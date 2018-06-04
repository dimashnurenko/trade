package com.trade.core.domain.tag

import com.trade.core.common.audit.CreateAudit
import org.springframework.hateoas.ResourceSupport
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

open class Tag(var tagId: Long?, var name: String?) {
	constructor() : this(null, null)
}

open class TagDto(var name: String) {
	constructor() : this("")
}

@Entity
@Table(name = "product_tag")
open class TagEntity(@Id
                     @GeneratedValue(strategy = IDENTITY)
                     var id: Long?,
                     @Column
                     var name: String?) {
	constructor() : this(null, null)
}

@Entity
@Table(name = "product_tag_mapping")
open class ProductTagMapping(@Id
                             @GeneratedValue(strategy = IDENTITY)
                             var id: Long?,
                             @Column(name = "tag_id")
                             var tagId: Long?,
                             @Column(name = "product_id")
                             var productId: Long?) : CreateAudit() {
	constructor() : this(null, null, null)
}

class TagResource(var name: String?,
                  var tagId: Long?) : ResourceSupport() {
	constructor() : this(null, null)
}
