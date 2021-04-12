package com.hnef.engine.player;

public enum MoveStatus {
    DONE{
        @Override
        public boolean isDone() {
            return true;
        }
    }, ILLEGAL_MOVE{
        @Override
        public boolean isDone() {
            return false;
        }
    };
    abstract boolean isDone();
}
