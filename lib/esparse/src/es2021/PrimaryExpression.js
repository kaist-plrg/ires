const Node = require('../Node');

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
  let Literal = require('./Literal');
  let IdentifierReference = require('./IdentifierReference');
  let ArrayLiteral= require('./ArrayLiteral');
  let ObjectLiteral = require('./ObjectLiteral');
  let FunctionExpression = require('./FunctionExpression');
  let ClassExpression = require('./ClassExpression');
  let GeneratorExpression = require('./GeneratorExpression');
  let AsyncFunctionExpression = require('./AsyncFunctionExpression');
  let AsyncGeneratorExpression= require('./AsyncGeneratorExpression');
  let TemplateLiteral = require('./TemplateLiteral');
  let CoverParenthesizedExpressionAndArrowParameterList = require('./CoverParenthesizedExpressionAndArrowParameterList');

  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'Identifier': {
      let ref = IdentifierReference(Yield, Await)(given);
      return new Node('PrimaryExpression', given, 1, [ref], params);
    }
    case 'Literal': {
      if (given.regex) {
        let regex = given.raw;
        return new Node('PrimaryExpression', given, 10, [regex], params);
      } else {
        let literal = Literal(given);
        return new Node('PrimaryExpression', given, 2, [literal], params);
      }
    }
    case 'ArrayExpression': {
      let arr = ArrayLiteral(Yield, Await)(given);
      return new Node('PrimaryExpression', given, 3, [arr], params);
    }
    case 'ObjectExpression': {
      let obj = ObjectLiteral(Yield, Await)(given);
      return new Node('PrimaryExpression', given, 4, [obj], params);
    }
    case 'FunctionExpression': {
      let { async, generator } = given;
      let index, func;
      if (!async && !generator) {
        index = 5;
        func = FunctionExpression(given);
      } else if (async && !generator) {
        index = 7;
        func = GeneratorExpression(given);
      } else if (!async && generator) {
        index = 8;
        func = AsyncFunctionExpression(given);
      } else {
        index = 9;
        func = AsyncGeneratorExpression(given);
      }
      return new Node('PrimaryExpression', given, index, [func], params);
    }
    case 'ClassExpression': {
      let expr = ClassExpression(Yield, Await)(given);
      return new Node('PrimaryExpression', given, 6, [expr], params);
    }
    case 'TemplateLiteral': {
      let temp = TemplateLiteral(Yield, Await, false)(given);
      return new Node('PrimaryExpression', given, 11, [expr], params);
    }
    case 'BinaryExpression': {
      let cover = CoverParenthesizedExpressionAndArrowParameterList(Yield, Await)(given);
      return new Node('PrimaryExpression', given, 12, [cover], params);
    }
    default:
      Node.TODO(`${type} @ PrimaryExpression`);
  }
  Node.TODO('PrimaryExpression');
}

module.exports = PrimaryExpression;
