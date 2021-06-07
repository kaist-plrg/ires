const assert = require('assert');
const { isArray } = Array;

function showTarget(f, target) {
  try {
    f()
  } catch (e) { 
    console.error('TARGET: ' + target);
    console.error('ERROR : ' + e.toString());
    throw e;
  }
}

function checkEqual(given, expected, target) {
  showTarget(() => assert.equal(given, expected), target);
}

function checkArray(given, target) {
  showTarget(() => assert(isArray(given)), target);
}

function check(given, expected, target) {
  if (isArray(expected)) {
    checkArray(given, target);
    checkEqual(given[0], expected[0], target + '.index');
    // check children
    checkElems(given[1], expected[1], target  + '.children');

    // check parser parameters
    checkElems(given[2], expected[2], target + '.params');
  } else {
    checkEqual(given, expected, target);
  }
}

function checkElems(given, expected, target) {
  checkArray(given, target);
  checkEqual(given.length, expected.length, target + '.length');
  for (let i = 0; i < expected.length; i++) {
    check(given[i], expected[i], `${target}[${i}]`);
  }
}

module.exports = {
  check,
};
