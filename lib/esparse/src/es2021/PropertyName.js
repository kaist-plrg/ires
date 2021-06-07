const Node = require('../Node');

let LiteralPropertyName = require('./LiteralPropertyName');
let ComputedPropertyName = require('./ComputedPropertyName');

// PropertyName[Yield, Await] :
//    LiteralPropertyName
//    ComputedPropertyName[?Yield, ?Await]
let PropertyName = (Yield, Await) => (given) => {
  let params = [Yield, Await];
  let { computed, key } = given;
  if (!computed) {
    let name = LiteralPropertyName(key);
    return new Node('PropertyName', given, 0, [name], params);
  } else {
    let name = ComputedPropertyName(Yield, Await)(key);
    return new Node('PropertyName', given, 1, [name], params);
  }
}

module.exports = PropertyName;
