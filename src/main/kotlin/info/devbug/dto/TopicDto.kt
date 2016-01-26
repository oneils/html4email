package info.devbug.dto

import javax.persistence.*

/**
 * @author Aliaksei Bahdanau
 */

@Entity
@Table(name = "topic")
class TopicDto() {
    constructor(name: String, orderPriority: Int) : this() {
        this.name = name
        this.orderPriority = orderPriority
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_topic_id_seq")
    @SequenceGenerator(name = "topic_topic_id_seq", sequenceName = "topic_topic_id_seq", allocationSize = 1)
    @Column(name = "topic_id")
    var id: Int = 0

    @Column(name = "topic_name")
    var name: String = ""

    @Column(name = "order_priority")
    var orderPriority: Int = 0

}