package com.trade.core.domain.tag

import javax.persistence.*

open class Tag(val name: String?, val productId: Long?) {
	constructor() : this(null, null)
}

open class TagDto(val name: String?, val productId: Long?) {
	constructor() : this(null, null)
}

@Entity
@Table(name = "product_tag")
open class TagEntity(@Id
                     @GeneratedValue
                     val id: Long?,
                     @Column
                     val name: String?,
                     @Column(name = "product_id")
                     val productId: Long?) {
	constructor() : this(null, null, null)
}