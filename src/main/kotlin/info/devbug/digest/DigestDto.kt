package info.devbug.digest

import java.util.*
import javax.persistence.*

/**
 * @author Aliaksei Bahdanau
 */

@Entity
@Table(name = "digest")
class DigestDto() {

    constructor(title: String): this() {
        this.title = title
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "digest_digest_id_seq")
    @SequenceGenerator(name = "digest_digest_id_seq", sequenceName = "digest_digest_id_seq", allocationSize = 1)
    @Column(name = "digest_id")
    var id: Int = 0

    var title: String = ""

    @Column(name = "published_date")
    var publishedDate: Date = Date()

    @Column(name = "created_date")
    var createdDate: Date = Date()
}