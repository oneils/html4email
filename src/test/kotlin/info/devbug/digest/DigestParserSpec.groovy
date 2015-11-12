package info.devbug.digest

import info.devbug.digest.DigestParser
import spock.lang.Specification

/**
 * @author Aliaksei Bahdanau.
 */
class DigestParserSpec extends Specification {
  def "getDigest should return a Digest object for specified file path"() {
    given:
    def digestFilePath = "src/test/resources/digest.json"
    def parser = new DigestParser()

    expect:
    def digest = parser.getDigest(digestFilePath)

    digest.title == "Digest #4"
    digest.contributeTo == "your_company@mail.com"
    digest.topics.size() == 2
  }
}
