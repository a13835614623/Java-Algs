package com.distribution.raft.role;

import com.distribution.raft.NodeType;
import com.distribution.raft.client.ClientRequest;
import com.distribution.raft.client.Command;
import com.distribution.raft.config.RaftConfig;
import com.distribution.raft.rpc.*;
import com.distribution.raft.util.ExecutorUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Node extends AbstractNode {
    /**
     * 数据
     */
    private Data data;
    /**
     * 节点的ID
     */
    private long id;
    /**
     * 节点类型
     */
    private NodeType nodeType;
    /**
     * 一个周期内是否有收到心跳
     */
    private boolean receiveHeartbeat;
    /**
     * 配置
     */
    private RaftConfig raftConfig;

    private RpcInvoker rpcInvoker;

    long start;

    public Node(long id) {
        super(id, NodeType.FOLLOWER);
    }

    public void increaseTerm() {
        data.setCurrentTerm(data.getCurrentTerm() + 1);
    }

    public void start() {
        start = System.currentTimeMillis();
        // 初始化为follower
        setFollower();
        // 处理收到的rpc请求
        asyncHandleRpcRequest();
        while (true) {
            // 如果一轮选举完后,没有人被选举为leader,此时自己还是候选人,则发起新一轮选举
            // 一个周期没有收到心跳信息,选举超时
            if (isFollower() || isCandidate()) {
                boolean isElectionTimeout = !receiveHeartbeat && System.currentTimeMillis() - start > raftConfig.getElectionTimeout();
                if (isElectionTimeout) {
                    startNewElection();
                }
            } else if (isLeader()) {
                initFollowerNextIndex();
                handleClientRequest();
            } else {

            }
        }
    }

    private void initFollowerNextIndex() {
        long nextIndex = data.getCurrentTerm() + 1;
        for (Follower follower : getFollowerList()) {
            follower.setNextIndex(nextIndex);
        }
    }

    public Log getLog(long index) {
        return data.getLogs().get((int) index);
    }

    /**
     * @description 处理客户端的请求
     * @return void
     * @author 张子宽
     * @date 2022/03/05
     */
    private void handleClientRequest() {
        ClientRequest clientRequest = acceptClientRequest();
        Command command = clientRequest.command();
        LinkedList<Log> logs = data.getLogs();
        LogImpl newLog = new LogImpl()
                .setCommand(command)
                .setIndex(logs.size())
                .setTerm(data.getCurrentTerm());
        logs.add(newLog);
        List<Follower> followerList = getFollowerList();
        int safeSuccessCount = followerList.size() >> 1;
        AtomicInteger success = new AtomicInteger();
        for (Follower follower : followerList) {
            ExecutorUtil.submit(() -> {
                long nextIndex = follower.getNextIndex();
                do {
                    Log lastLog = getLog(nextIndex - 1);
                    AppendEntriesRpcRequest request = new AppendEntriesRpcRequest(follower.id());
                    request.setLeaderId(id);
                    request.setPrevLogTerm(lastLog.term());
                    request.setPrevLogIndex(lastLog.index());
                    request.setEntries(data.getLogs().subList((int) nextIndex, data.getLogs().size() - 1));
                    request.setTerm(data.getCurrentTerm());
                    AppendEntriesRpcResponse response = rpcInvoker.invoke(request);
                    // 失败重试,直到成功
                    if (!response.isSuccess()) {
                        follower.setNextIndex(--nextIndex);
                    } else {
                        // 超过一半服务器已复制成功,则认为是这条日志是commited(可被提交的)
                        boolean commited = success.incrementAndGet() >= safeSuccessCount;
                        if (commited) {
                            commitLog(newLog);
                        }
                    }
                } while (true);
            });
        }
    }

    /**
     * @description 提交日志到状态机
     * @param newLog 日志
     * @return void
     * @author 张子宽
     * @date 2022/03/05
     */
    private void commitLog(LogImpl newLog) {
        newLog.setCommitted(true);
    }


    private ClientRequest acceptClientRequest() {
        return null;
    }

    private boolean isFollower() {
        return nodeType == NodeType.FOLLOWER;
    }

    private boolean isLeader() {
        return nodeType == NodeType.LEADER;
    }

    /**
     * @description 发起一次新的选举
     * @return void
     * @author 张子宽
     * @date 2022/02/27
     */
    private void startNewElection() {
        // 自增任期
        increaseTerm();
        // 转换为候选人
        nodeType = NodeType.CANDIDATE;
        // 给自己投票
        // 给其他服务器发送 RequestVoteRpc
        sendVoteRequest(getNodeList());
    }

    private void sendVoteRequest(List<Node> nodeList) {
        Log lastLog = data.getLogs().getLast();
        List<VoteRpcResponse> responses = new ArrayList<>(nodeList.size());
        for (Node node : nodeList) {
            VoteRpcRequest voteRpcRequest = new VoteRpcRequest(node.id);
            voteRpcRequest.setTerm(data.getCurrentTerm());
            voteRpcRequest.setCandidateId(id);
            voteRpcRequest.setLastLogIndex(lastLog.index());
            voteRpcRequest.setLastLogTerm(lastLog.term());
            responses.add(rpcInvoker.invoke(voteRpcRequest));
        }
        // 赢得选择需要的最低选票的数量
        int winElectionNeedVoteCount = nodeList.size() >> 1 + 1;
        // 收到选票的数量,默认投给自己一票
        long voteGrantedCount = 1;
        for (VoteRpcResponse response : responses) {
            // 其他人成为leader,自己回退为follower
            if (response.getTerm() >= data.getCurrentTerm()) {
                nodeType = NodeType.FOLLOWER;
                break;
            } else if (response.isVoteGranted()) {
                voteGrantedCount++;
                // 赢得选举,自己变为leader
                if (voteGrantedCount >= winElectionNeedVoteCount) {
                    nodeType = NodeType.LEADER;
                }
            }
        }
    }

    private List<Node> getNodeList() {
        return new ArrayList<>();
    }

    private List<Follower> getFollowerList() {
        return new ArrayList<>();
    }

    private void asyncHandleRpcRequest() {
        ExecutorUtil.submit(() -> {
            RpcRequest rpcRequest = accept();
            if (rpcRequest instanceof AppendEntriesRpcRequest) {
                handleAppendLogEntriesRequest(((AppendEntriesRpcRequest) rpcRequest));
            } else if (rpcRequest instanceof VoteRpcRequest) {
                handleVoteRpcRequest(((VoteRpcRequest) rpcRequest));
            }
        });
    }

    private RpcRequest accept() {
        return null;
    }

    /**
     * @description 处理选举请求
     * @param request 响应
     * @return void
     * @author 张子宽
     * @date 2022/02/27
     */
    private VoteRpcResponse handleVoteRpcRequest(VoteRpcRequest request) {
        VoteRpcResponse response = new VoteRpcResponse();
        long currentTerm = data.getCurrentTerm();
        response.setTerm(currentTerm);
        if (request.getTerm() < currentTerm) {
            response.setVoteGranted(false);
            return response;
        }
        String votedFor = data.getVotedFor();
        if (votedFor == null || votedFor.equals(request.getCandidateId()+"")) {
            response.setVoteGranted(true);
        } else {
            response.setVoteGranted(false);
        }
        return response;
    }


    /**
     * @description 加日志
     * @param request 请求
     * @return void
     * @author 张子宽
     * @date 2022/02/27
     */
    private AppendEntriesRpcResponse handleAppendLogEntriesRequest(AppendEntriesRpcRequest request) {
        AppendEntriesRpcResponse response = new AppendEntriesRpcResponse();
        long currentTerm = data.getCurrentTerm();
        response.setTerm(currentTerm);
        if (request.getTerm() < currentTerm) {
            response.setSuccess(false);
            return response;
        }
        // 任期大于当前任期
        if (isCandidate() && request.getTerm() >= currentTerm) {
            setFollower();
        }
        response.setSuccess(true);
        if (request.isHeartbeat()) {
            receiveHeartbeat = true;
        } else {
            List<Log> entries = request.getEntries();
            LinkedList<Log> logs = data.getLogs();
            for (Log entry : entries) {
                int index = (int) entry.index();
                Log log = logs.get(index);
                if (log != null) {
                    if (log.term() != entry.term()) {
                        logs.removeIf(l -> l.index() >= index);
                        logs.add(entry);
                    }
                } else {
                    logs.add(entry);
                }
            }
            if (request.getLeaderCommit() > data.getCommitIndex()) {
                data.setCommitIndex(Math.min(request.getLeaderCommit(), logs.getLast().index()));
            }
        }
        return response;
    }

    private void setFollower() {
        nodeType = NodeType.FOLLOWER;
    }

    private boolean isCandidate() {
        return nodeType == NodeType.CANDIDATE;
    }

    private void leader() {
        nodeType = NodeType.LEADER;
    }

    private void candidate() {
        nodeType = NodeType.CANDIDATE;
    }

    @Override
    public NodeType type() {
        return nodeType;
    }
}
