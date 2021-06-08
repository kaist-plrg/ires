const Node = require('../Node');

// LexicalDeclaration[In, Yield, Await] :
//    LetOrConst BindingList[?In, ?Yield, ?Await] ;
let LexicalDeclaration = (In, Yield, Await) => (given) => {
  const BindingList = require('./BindingList');
  let params = [In, Yield, Await];
  let { kind, declarations } = given;
  let pre = new Node('LetOrConst', {}, 0);
  if (kind == 'const') pre.index = 1;
  let list = BindingList(In, Yield, Await)(declarations);
  return new Node('LexicalDeclaration', given, 0, [pre, list], params);
}

module.exports = LexicalDeclaration;
