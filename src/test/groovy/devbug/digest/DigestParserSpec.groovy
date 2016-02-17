package devbug.digest

import info.devbug.digest.repository.DigestDto
import info.devbug.digest.util.DigestParser
import spock.lang.Specification

/**
 * @author Aliaksei Bahdanau.
 */
class DigestParserSpec extends Specification {
  private DigestParser parser

  def setup() {
    parser = new DigestParser()
  }

  def "getDigest should return a Digest object for specified file path"() {
    given:
    def digestFilePath = "src/test/resources/digest.json"

    expect:
    DigestDto digest = parser.getDigest(digestFilePath)

    digest.title == "Digest #4"
    digest.contributeTo == "your_company@mail.com"
    digest.topics.size() == 2
  }

  def "getDigestNumber should return current digest number"() {
    given:
    def digestTitle = "BackEnd Digest #12"

    expect:
    def digestNumber = parser.getDigestNumber(digestTitle)

    digestNumber == "12"
  }

  def "getDigestNumber should return default number"() {
    given:
    def digestTitle = ""

    expect:
    def digestNumber = parser.getDigestNumber(digestTitle)

    digestNumber == parser.DEFAULT_DIGEST_NUMBER
  }
}