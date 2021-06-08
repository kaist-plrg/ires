const Node = require('../Node');

// CoverParenthesizedExpressionAndArrowParameterList[Yield, Await] :
//    ( Expression[+In, ?Yield, ?Await] )
//    ( Expression[+In, ?Yield, ?Await] , )
//    ( )
//    ( ... BindingIdentifier[?Yield, ?Await] )
//    ( ... BindingPattern[?Yield, ?Await] )
//    ( Expression[+In, ?Yield, ?Await] , ... BindingIdentifier[?Yield, ?Await] )
//    ( Expression[+In, ?Yield, ?Await] , ... BindingPattern[?Yield, ?Await] )
let CoverParenthesizedExpressionAndArrowParameterList = (Yield, Await) => (given) => {
  const Expression = require('./Expression');
  let params = [Yield, Await];
  let expr = Expression(true, Yield, Await)(given);
  return new Node('CoverParenthesizedExpressionAndArrowParameterList', given, 0, [expr], params)
}

module.exports = CoverParenthesizedExpressionAndArrowParameterList;
