package com.trade.domain.image;

import com.trade.common.datasource.AbstractCustomRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.type.StandardBasicTypes.LONG;

@Repository
public class ProductImageBinaryRepositoryImpl extends AbstractCustomRepo implements ProductImageBinaryRepositoryCustom {

	@Override
	public List<Long> findIds(Long productId) {
		String sql = "SELECT " +
				"pib.id as id " +
				"FROM product_image_binary pib " +
				"WHERE pib.product_id=:productId";
		return getSession().createSQLQuery(sql)
		                   .addScalar("id", LONG)
		                   .setParameter("productId", productId)
		                   .list();
	}
}
