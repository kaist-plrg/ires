const Node = require('../Node');

let Literal = require('./Literal');
let IdentifierReference = require('./IdentifierReference');
let ObjectLiteral = require('./ObjectLiteral');

// PrimaryExpression[Yield, Await] :
//    this
//    IdentifierReference[?Yield, ?Await]
//    Literal
//    ArrayLiteral[?Yield, ?Await]
//    ObjectLiteral[?Yield, ?Await]
//    FunctionExpression
//    ClassExpression[?Yield, ?Await]
//    GeneratorExpression
//    AsyncFunctionExpression
//    AsyncGeneratorExpression
//    RegularExpressionLiteral
//    TemplateLiteral[?Yield, ?Await, ~Tagged]
//    CoverParenthesizedExpressionAndArrowParameterList[?Yield, ?Await]
let PrimaryExpression = (Yield, Await) => (given) => {
  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'Identifier': {
      let ref = IdentifierReference(Yield, Await)(given);
      return new Node('PrimaryExpression', given, 1, [ref], params);
    }
    case 'Literal': {
      let literal = Literal(given);
      return new Node('PrimaryExpression', given, 2, [literal], params);
    }
    case 'ObjectExpression': {
      let obj = ObjectLiteral(Yield, Await)(given);
      return new Node('PrimaryExpression', given, 4, [obj], params);
    }
    default:
      Node.TODO(`${type} @ PrimaryExpression`);
  }
  Node.TODO('PrimaryExpression');
}

module.exports = PrimaryExpression;
