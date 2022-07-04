package com.distribution.transcation.threepc;

import com.distribution.transcation.Transaction;

public class ThreeTransaction implements Transaction {

    public boolean canCommit(){

        return false;
    }

    public void preCommit(){

    }

    public void doCommit(){

    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }
}
