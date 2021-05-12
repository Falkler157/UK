package sort;

import model.Log;

public interface ITreeVisitor {
    Log visit(Tree node);
}
