const Node = require('../Node');

let PrimaryExpression = require('./PrimaryExpression');

// MemberExpression[Yield, Await] :
//    PrimaryExpression[?Yield, ?Await]
//    MemberExpression[?Yield, ?Await] [ Expression[+In, ?Yield, ?Await] ]
//    MemberExpression[?Yield, ?Await] . IdentifierName
//    MemberExpression[?Yield, ?Await] TemplateLiteral[?Yield, ?Await, +Tagged] 
//    SuperProperty[?Yield, ?Await]
//    MetaProperty
//    new MemberExpression[?Yield, ?Await] Arguments[?Yield, ?Await]
let MemberExpression = (Yield, Await) => (given) => {
  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'Identifier':
    case 'Literal': {
      let expr = PrimaryExpression(Yield, Await)(given);
      return new Node('MemberExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ MemberExpression`);
  }
  Node.TODO('MemberExpression');
}

module.exports = MemberExpression;
