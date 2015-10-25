from dbms.vendor.btree import BPlusTree


class Index(object):
    __slots__ = ['btree']

    def __init__(self):
        self.btree = BPlusTree(2)
