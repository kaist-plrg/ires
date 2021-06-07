const Node = require('../Node');

let RelationalExpression = require('./RelationalExpression');

// EqualityExpression[In, Yield, Await] :
//    RelationalExpression[?In, ?Yield, ?Await]
//    EqualityExpression[?In, ?Yield, ?Await] == RelationalExpression[?In, ?Yield, ?Await]
//    EqualityExpression[?In, ?Yield, ?Await] != RelationalExpression[?In, ?Yield, ?Await]
//    EqualityExpression[?In, ?Yield, ?Await] === RelationalExpression[?In, ?Yield, ?Await]
//    EqualityExpression[?In, ?Yield, ?Await] !== RelationalExpression[?In, ?Yield, ?Await]
let EqualityExpression = (In, Yield, Await) => (given) => {
  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = RelationalExpression(In, Yield, Await)(given);
      return new Node('EqualityExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ EqualityExpression`);
  }
  Node.TODO('EqualityExpression');
}

module.exports = EqualityExpression;
