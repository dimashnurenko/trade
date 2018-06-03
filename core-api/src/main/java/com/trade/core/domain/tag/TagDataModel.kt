package com.trade.core.domain.tag

import javax.persistence.*

open class Tag(var name: String?, var productId: Long?) {
	constructor() : this(null, null)
}

open class TagDto(var name: String?, var productId: Long?) {
	constructor() : this(null, null)
}

@Entity
@Table(name = "product_tag")
open class TagEntity(@Id
                     @GeneratedValue
                     var id: Long?,
                     @Column
                     var name: String?,
                     @Column(name = "product_id")
                     var productId: Long?) {
	constructor() : this(null, null, null)
}