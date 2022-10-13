$(document).ready(function(){
	show_order_summary();


	//place order
	$('div.cart-detail').on('click','.btn-place-order',function(evt)
	{
		evt.preventDefault();
		
		//reciver information obj
		let receiverInfo = {
			name : $('#r-name').val(),
			email : $('#r-email').val(),
			phone : $('#r-phone').val(),
			address : $('#r-address').val()
		}
		//Order Items 
		let cartItemList=localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList);
		
		//Order Info (reciver + order Item)
		let orderInfo = {
			reciver:receiverInfo,
			orderItems:cartItemList_obj,
			
		};
		//call server with ajax
		$.ajax({
			beforeSend:function(xhr) {
				xhr.setRequestHeader('X-CSRF-Token',$('meta[name="csrf-token"]').attr('content'))
			},
			type:"POST",
			contentType:'application/json',
			dataType:'json',
			data:JSON.stringify(orderInfo),
			url:'/cart/place-order',
			success:function(result){
				if(result == "") {
					alert("Something is wrong.Try again.")
				} else {
					localStorage.clear();
					alert("Your Order Have Been successfully . Your Order Id is " + result+"\n thanks")
					window.location.href = "/";
				}
			},
			error:function(result) {
				alert("Something is wrong")
			}
		})//ajax function
	})//function closed

	function show_order_summary() {
		let cartItemList=localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList);
		let total = 0;
		$.each(cartItemList_obj,function(i,prod){
			let sub_total = prod.qty*prod.productPrice;
			total += sub_total;
		})
		$('.total').text(total+"Ks");
		$('.grand-total').text(total+"Ks");
	}//function closed
})//main 