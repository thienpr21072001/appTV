package com.app.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Product;
import com.app.model.PagingSearchFilterProduct;
import com.app.model.entity.ProductSpecification;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;
import com.app.utils.Constant;
import com.app.utils.ProvinceUtil;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepo;
	
	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


	@Override
	public Product createProduct(Product products) {
		// TODO Auto-generated method stub
		products.setCreateDate(new Date());
		products.setUpdateDate(new Date());
		products.setStatus(1);
		for(ProvinceUtil provinceUtil: ProvinceUtil.values()) {
			if(provinceUtil.getValue() == products.getProvinceId()) {
				products.setProvinceText(provinceUtil.getText());
				break;
			}
		}
		 
		if(!products.getMultipartFile().isEmpty() ) {
			String  imagesName = uploadFile(products.getMultipartFile());
			products.setImages(imagesName);
			System.out.println(imagesName);
		}
		return productRepo.save(products);
	}
	private String uploadFile(MultipartFile multipartFile)   { 
		String imagesName = null;
		try {
			imagesName = System.currentTimeMillis() + multipartFile.getOriginalFilename();
			File file = new File(Constant.PATH_UPLOAD_IMAGES + imagesName);
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
			bufferedOutputStream.write(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 
			log.warn("Upload file faild !!");
		}
		return "/upload/"+imagesName;
	}

	@Override
	public Product updateProduct(Product products) {
		// TODO Auto-generated method stub
		products.setUpdateDate(new Date());
		for(ProvinceUtil provinceUtil: ProvinceUtil.values()) {
			if(provinceUtil.getValue() == products.getProvinceId()) {
				products.setProvinceText(provinceUtil.getText());
				break;
			}
		}
		
		if(!products.getMultipartFile().isEmpty()) {
			String  imagesName = uploadFile(products.getMultipartFile());
			products.setImages(imagesName);
		}
		products.setStatus(1);
		return productRepo.save(products);
	}

	@Override
	public void deleteProducts(Product products) {
		// TODO Auto-generated method stub
		//productRepo.delete(products);
		products.setStatus(0);
		productRepo.save(products);
	}

	@Override
	public Product getById(long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).orElse(null);
	}

	@Override
	public Product getByName(String name) {
		// TODO Auto-generated method stub
		return productRepo.findByName(name);
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Page<Product> getAll(PagingSearchFilterProduct psfp) {
		// TODO Auto-generated method stub
		return productRepo.findAll(new ProductSpecification(psfp.getKeyword(), psfp.getProvinceId()), PageRequest.of(psfp.getPage(), psfp.getPageSize()));
	}
	 
	

}
