$(document).ready(function(){
	//alert("hi");
	initializeLocalStorage();
	showCartCount();
	showCart();
	
	
	//update quantity
	$('tbody.cart-items').on('change','.update-qty',function(){
		let id_val = $(this).data('id');
		let qty_val = $(this).val();
		let cartItemList =localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList);
		
		let update_index = -1;
		let update_product = '';
		$.each(cartItemList_obj,function(i,prod){
			if(prod.productId == id_val) {
				update_index =i;
				update_product = prod;
			}
			
		});
		//update
		update_product.qty = qty_val;
		cartItemList_obj[update_index] = update_product; 
		localStorage.setItem('cart-item-data',JSON.stringify(cartItemList_obj));
		showCart();
	})
	
	
	//remove product from cart
	$('tbody.cart-items').on('click','.btn-remove',function(evt){
		evt.preventDefault();
		let id_val = $(this).data('id');
		let cartItemList =localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList);
		let delete_index = -1;
		$.each(cartItemList_obj,function(i,prod){
			if(prod.productId==id_val) {
				delete_index = i;
			}
			
		});
		cartItemList_obj.splice(delete_index,1);
		localStorage.setItem('cart-item-data',JSON.stringify(cartItemList_obj));
		showCartCount();
		showCart();
	})
	
	//add product to cart
	$('div.product').on('click','a.add-to-cart',function(evt){
		evt.preventDefault();
		let id_val = $(this).data('id');
		let name_val = $(this).data('name');
		let price_val = $(this).data('price');
		
		let cartItemList =localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList);
		
		let flag = false;
		let found_index = -1;
		let old_product = '';
		$.each(cartItemList_obj,function(i,product) {
			if(id_val == product.productId) {
				flag = true;
				found_index = i;
				old_product = product;
			}
		});
		
		if(flag) {//old product
			old_product.qty=old_product.qty+1;
			cartItemList_obj[found_index] = old_product;
		} else { // new product
			//new Product 
			let new_product = {
				productId:id_val,
				productName:name_val,
				productPrice:price_val,
				qty:1
			};
			cartItemList_obj.push(new_product);
		}
		//store array obj to localStorage
		localStorage.setItem('cart-item-data',JSON.stringify(cartItemList_obj));
		showCartCount();
	})
	
	function showCart() {
		let cartItemList = localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList); //Json to array Obj
		let html = '';
		let total = 0;
		$.each(cartItemList_obj,function(i,prod){
			let sub_total = prod.qty * prod.productPrice;
			total +=sub_total;
			let tr = `
			<tr class="text-center">
						        <td class="product-remove"><a href="#" data-id = "${prod.productId}" class = "btn-remove"><span class="ion-ios-close"></span></a></td>
								 <td class="product-name">
						        	<h6>${prod.productName}</h6>
						        </td>
						        
						        <td class="price">${prod.productPrice}</td>
						        
						        <td class="quantity">
						        	<div class="input-group mb-3">
					             	<input type="number" data-id="${prod.productId}" name="quantity" class="quantity form-control input-number update-qty" value="${prod.qty}" min="1" max="100">
					          	</div>
					          </td>
						        
						        <td>${sub_total} Ks.</td>
						      </tr>
			`;
			html+= tr;
		});
		$('tbody.cart-items').html(html);
		$('.total').text(total + "Ks");
		$('.grand-total').text(total + "Ks")
		if(cartItemList_obj.length==0) {
			$('a.btn-checkout').removeAttr('href');
		}
		
	}
	
	function showCartCount() {
		let cartItemList = localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList); //Json to array Obj
		$('.cart-count').text(cartItemList_obj.length);
	}
	
	
	function initializeLocalStorage() {
		let cartItemList = localStorage.getItem('cart-item-data');
		if(! cartItemList) {
			cartItemList = [];
			localStorage.setItem('cart-item-data',JSON.stringify(cartItemList));
		}
		
		//Json => {"id" : 1 , "name" : product1},{"id" : 2 , "name" : product2}
		
	}
});