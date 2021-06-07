class Node {
  constructor(kind, elem, index, children, params) {
    if (children === undefined) children = [];
    if (params === undefined) params = [];
    this.kind = kind;
    this.start = elem.start;
    this.end = elem.end;
    this.index = index;
    this.children = children;
    this.params = params;
  }

  compress() {
    return [
      this.index,
      this.children.map(child => {
        if (child?.compress === undefined) return child;
        else return child.compress();
      }),
      this.params.map(param => Number(param)),
    ];
  }
}
Node.fromList = (kind, elems, genChild, params) => {
  let list;
  for (let elem of elems) {
    let child = genChild(elem);
    if (list === undefined) {
      list = new Node(kind, elem, 0, [child], params);
    } else {
      list = new Node(kind, elem, 1, [list, child], params);
      list.end = child.end;
    }
  }
  return list;
}
Node.getRhs = (nameList, genList, given) => {
  let index = nameList.indexOf(given.type);
  if (index === -1) return null;
  let child = genList[index](given);
  return { index, child };
}
Node.TODO = (msg) => {
  throw `[TODO] ${msg}`
};

module.exports = Node
