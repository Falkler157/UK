package sort;

import model.Log;

import java.util.List;

public interface ITreeSort {

    /**
     * insert (добавление нового поддерева (ключа))
     * сравнить ключ добавляемого поддерева (К) с ключом корневого узла (X).
     * Если K>=X, рекурсивно добавить новое дерево в правое поддерево.
     * Если K<X, рекурсивно добавить новое дерево в левое поддерево.
     * Если поддерева нет, то вставить на это место новое дерево
     */
    void insert(Tree aTree);

    /**
     * traverse (обход)
     * Рекурсивно обойти левое поддерево.
     * Рекурсивно обойти правое поддерево.
     */
    int traverse(ITreeVisitor visitor, List<Log> array, int pos);
}
