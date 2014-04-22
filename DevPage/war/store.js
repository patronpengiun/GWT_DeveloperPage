
function buildStore() {

  var store = $('#store')[0];

  var storeHeader = document.createElement('div');
  storeHeader.id           = 'store_title';
  store.appendChild(storeHeader);

  var storeImage = document.createElement('img');
  storeImage.setAttribute('src', 'images/store64.png');
  storeHeader.appendChild(storeImage);

  var storeTitle = document.createElement('h1');
  storeTitle.id         = 'store_title';
  storeTitle.innerHTML  = 'Store';
  storeHeader.appendChild(storeTitle);

  showProducts();
}

function showProducts() {
	var store = $('#store')[0];

	var storeContents = $('#store_contents')[0] || document.createElement('div');
	storeContents.id = 'store_contents';
	storeContents.innerHTML = "";
	if(store.children.length < 2) store.appendChild(storeContents);

	//Coins
	var buy_coins1 = document.createElement('div');
	buy_coins1.className = "store_package";
	buy_coins1.innerHTML = "<img src='images/coin_bundle_icon.png'/>";
	buy_coins1.innerHTML += "<h3 class='quantity'>10 coins</h3>";
	buy_coins1.innerHTML += "<span></span>";
	buy_coins1.innerHTML += "<h3>$1.00</h3>";
	buy_coins1.innerHTML += "<div class='button_small'>Buy!</div>";
	buy_coins1.setAttribute("onClick","javascript:buyCoins(10,1)");
	storeContents.appendChild(buy_coins1);

	var buy_coins2 = document.createElement('div');
	buy_coins2.className = "store_package";
	buy_coins2.innerHTML = "<img src='images/coin_bundle_icon.png'/>";
	buy_coins2.innerHTML += "<h3 class='quantity'>20 coins</h3>";
	buy_coins2.innerHTML += "<span></span>";
	buy_coins2.innerHTML += "<h3>$2.00</h3>";
	buy_coins2.innerHTML += "<div class='button_small'>Buy!</div>";
	buy_coins2.setAttribute("onClick","javascript:buyCoins(20, 2)");
	storeContents.appendChild(buy_coins2);
	
	var buy_coins3 = document.createElement('div');
	buy_coins3.className = "store_package";
	buy_coins3.innerHTML = "<img src='images/coin_bundle_icon.png'/>";
	buy_coins3.innerHTML += "<h3 class='quantity'>30 coins</h3>";
	buy_coins3.innerHTML += "<span></span>";
	buy_coins3.innerHTML += "<h3>$3.00</h3>";
	buy_coins3.innerHTML += "<div class='button_small'>Buy!</div>";
	buy_coins3.setAttribute("onClick","javascript:buyCoins(30, 3)");
	storeContents.appendChild(buy_coins3);
	
	var buy_coins4 = document.createElement('div');
	buy_coins4.className = "store_package";
	buy_coins4.innerHTML = "<img src='images/coin_bundle_icon.png'/>";
	buy_coins4.innerHTML += "<h3 class='quantity'>40 coins</h3>";
	buy_coins4.innerHTML += "<span></span>";
	buy_coins4.innerHTML += "<h3>$4.00</h3>";
	buy_coins4.innerHTML += "<div class='button_small'>Buy!</div>";
	buy_coins4.setAttribute("onClick","javascript:buyCoins(40, 4)");
	storeContents.appendChild(buy_coins4);
	
	var buy_coins5 = document.createElement('div');
	buy_coins5.className = "store_package";
	buy_coins5.innerHTML = "<img src='images/coin_bundle_icon.png'/>";
	buy_coins5.innerHTML += "<h3 class='quantity'>50 coins</h3>";
	buy_coins5.innerHTML += "<span></span>";
	buy_coins5.innerHTML += "<h3>$5.00</h3>";
	buy_coins5.innerHTML += "<div class='button_small'>Buy!</div>";
	buy_coins5.setAttribute("onClick","javascript:buyCoins(50, 5)");
	storeContents.appendChild(buy_coins5);
	
	var buy_coins6 = document.createElement('div');
	buy_coins6.className = "store_package";
	buy_coins6.innerHTML = "<img src='images/coin_bundle_icon.png'/>";
	buy_coins6.innerHTML += "<h3 class='quantity'>100 coins</h3>";
	buy_coins6.innerHTML += "<span></span>";
	buy_coins6.innerHTML += "<h3>$10.0</h3>";
	buy_coins6.innerHTML += "<div class='button_small'>Buy!</div>";
	buy_coins6.setAttribute("onClick","javascript:buyCoins(100, 10)");
	storeContents.appendChild(buy_coins6);
}

function buyCoins(quantity, price) {
	
}