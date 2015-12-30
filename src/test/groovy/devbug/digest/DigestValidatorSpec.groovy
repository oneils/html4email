package devbug.digest

import groovy.transform.TypeChecked
import info.devbug.digest.DigestValidator
import spock.lang.Specification

/**
 * @author Aliaksei Bahdanau.
 */
@TypeChecked
class DigestValidatorSpec extends Specification {
  DigestValidator validator

  def setup() {
    validator = new DigestValidator()
  }
  def "IsTitleValid should return true for valid input"() {
    given:
    def title = "BackEnd digest #12"

    expect:
    def result = validator.isTitleValid(title)
    result
  }

  def "IsTitleValid should return false for double ##"() {
    given:
    def title = "BackEnd digest ##12"

    expect:
    def result = validator.isTitleValid(title)
    !result
  }

  def "IsTitleValid should return false for more then one #"() {
    given:
    def title = "BackEnd # digest #12"

    expect:
    def result = validator.isTitleValid(title)
    !result
  }

  def "IsTitleValid should return false for empty input"() {
    given:
    def title = ""

    expect:
    def result = validator.isTitleValid(title)
    !result
  }
}