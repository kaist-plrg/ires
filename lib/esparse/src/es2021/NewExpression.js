const Node = require('../Node');

let MemberExpression = require('./MemberExpression');

// NewExpression[Yield, Await] :
//    MemberExpression[?Yield, ?Await]
//    new NewExpression[?Yield, ?Await]
let NewExpression = (Yield, Await) => (given) => {
  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'Identifier':
    case 'Literal': {
      let expr = MemberExpression(Yield, Await)(given);
      return new Node('NewExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ NewExpression`);
  }
  Node.TODO('NewExpression');
}

module.exports = NewExpression;
