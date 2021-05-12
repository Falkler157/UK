package sort;

import model.Log;

import java.util.List;

public class Tree implements ITreeSort {
    public Tree left;
    public Tree right;
    public Log key;

    public Tree(Log k) {
        key = k;
    }

    public void insert(Tree aTree) {
        if (aTree.key.getCreated_at().compareTo(key.getCreated_at()) < 0)
            if (left != null) left.insert(aTree);
            else left = aTree;
        else if (right != null) right.insert(aTree);
        else right = aTree;
    }

    public int traverse(ITreeVisitor visitor, List<Log> array, int pos) {
        if (left != null)
            pos = left.traverse(visitor, array, pos);

        array.add(pos++, visitor.visit(this));

        if (right != null)
            pos = right.traverse(visitor, array, pos);
        return pos;
    }
}
