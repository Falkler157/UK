package sort;

import model.Log;

public class Key implements ITreeVisitor {

    @Override
    public Log visit(Tree node) {
        return node.key;
    }
}
