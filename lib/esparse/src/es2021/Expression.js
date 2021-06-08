const Node = require('../Node');

// Expression[In, Yield, Await] :
//    AssignmentExpression[?In, ?Yield, ?Await]
//    Expression[?In, ?Yield, ?Await] , AssignmentExpression[?In, ?Yield, ?Await]
let Expression = (In, Yield, Await) => (given) => {
  let AssignmentExpression = require('./AssignmentExpression');

  let params = [In, Yield, Await];
  let { type, expressions } = given;
  switch (type) {
    case 'SequenceExpression': {
      let genChild = AssignmentExpression(In, Yield, Await);
      return Node.fromList('Expression', expressions, genChild, params);
    }
    default: {
      let expr = AssignmentExpression(In, Yield, Await)(given);
      return new Node('Expression', given, 0, [expr], params);
    }
  }
}

module.exports = Expression;
