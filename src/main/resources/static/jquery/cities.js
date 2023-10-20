const pathdeApp = "jpasample";
let pageNum = /*[[${extend.pageInfo.getNumber()+1}]]*/{};
let totalPages = /*[[${extend.pageInfo.getTotalPages()}]]*/{};
let totalRecords, searchName;
$("#searchBtn").on('click', function() {
	searchName = $("#keywordInput").val().trim().toString();
	toSelectedPg(1, searchName);
});
$("#toFirst").on('click', function(e) {
	e.preventDefault();
	searchName = $("#keywordInput").val().trim().toString();
	toSelectedPg(1, searchName);
});
$("#toLast").on('click', function(e) {
	e.preventDefault();
	searchName = $("#keywordInput").val().trim().toString();
	toSelectedPg(totalPages, searchName);
});
function toSelectedPg(pageNum, searchName) {
	$.ajax({
		url: pathdeApp + '/city',
		data: {
			'pageNum': pageNum,
			'keyword': searchName
		},
		type: 'GET',
		success: function(result) {
			buildCityTable(result);
			buildPageInfos(result);
			buildPageNavi(result);
		}
	})
}
function buildCityTable(result) {
	$("#cityTableBody").empty();
	let index = result.extend.pageInfo.records;
	$.each(index, (index, item) => {
		let cityName = item.name;
		let nationName = item.nation;
		let districtName = item.district;
		let languageName = item.language;
		let idTd = $("<th scope='row' class='text-center' style='width:70px;vertical-align:bottom;'></th>").append(item.id);
		let nameTd;
		if (cityName.length >= 15) {
			nameTd = $("<td class='text-center' style='width:120px;font-size:10px;vertical-align:bottom;'></td>").append(cityName);
		} else {
			nameTd = $("<td class='text-center' style='width:120px;font-size:15px;vertical-align:bottom;'></td>").append(cityName);
		}
		let continentTd = $("<td class='text-center' style='width:100px;vertical-align:bottom;'></td>").append(item.continent);
		let nationTd;
		if (nationName.length > 12 && nationName.length <= 15) {
			nationTd = $("<td class='text-center' style='width:100px;font-size:12px;vertical-align:bottom;'></td>").append(nationName);
		} else if (nationName.length > 15) {
			nationTd = $("<td class='text-center' style='width:100px;font-size:10px;vertical-align:bottom;'></td>").append(nationName);
		} else {
			nationTd = $("<td class='text-center' style='width:100px;font-size:15px;vertical-align:bottom;'></td>").append(nationName);
		}
		let districtTd;
		if (districtName.length >= 15) {
			districtTd = $("<td class='text-center' style='width:120px;font-size:10px;vertical-align:bottom;'></td>").append(districtName);
		} else {
			districtTd = $("<td class='text-center' style='width:120px;font-size:15px;vertical-align:bottom'></td>").append(districtName);
		}
		let populationTd = $("<td class='text-center' style='width:70px;vertical-align:bottom;'></td>").append(item.population);
		let languageTd;
		if (languageName.length >= 15) {
			languageTd = $("<td class='text-center' style='width:80px;font-size:10px;vertical-align:bottom;'></td>").append(languageName);
		} else {
			languageTd = $("<td class='text-center' style='width:80px;font-size:15px;vertical-align:bottom;'></td>").append(languageName);
		}
		let editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
			.append($("<i class='bi bi-pencil-fill'></i>")).append("編集");
		editBtn.attr("editId", item.id);
		let deleteBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
			.append($("<i class='bi bi-trash'></i>")).append("削除");
		deleteBtn.attr("deleteId", item.id);
		let btnTd = $("<td class='text-center' style='width:120px;vertical-align:bottom;'></td>").append(editBtn).append(" ").append(deleteBtn);
		$("<tr></tr>").append(idTd).append(nameTd).append(continentTd).append(nationTd).append(districtTd).append(populationTd).append(languageTd)
			.append(btnTd).appendTo("#cityTableBody");
	});
}
function buildPageInfos(result) {
	let pageInfos = $("#pageInfos");
	pageInfos.empty();
	pageNum = result.extend.pageInfo.pageNum;
	totalPages = result.extend.pageInfo.totalPages;
	totalRecords = result.extend.pageInfo.totalRecords;
	pageInfos.append("The " + pageNum + " page in " + totalPages
		+ " pages, " + totalRecords + " records found.");
}
function buildPageNavi(result) {
	$("#initial-pagenavi").remove();
	$("#pageNavi").empty();
	let ul = $("<ul></ul>").addClass("pagination");
	let firstPageLi = $("<li class='page-item'></li>").append(
		$("<a class='page-link'></a>").append("最初のページへ").attr("href", "#"));
	let previousPageLi = $("<li class='page-item'></li>").append(
		$("<a class='page-link'></a>").append("&laquo;").attr("href", "#"));
	if (!result.extend.pageInfo.hasPreviousPage) {
		firstPageLi.addClass("disabled");
		previousPageLi.addClass("disabled");
	} else {
		firstPageLi.click(function() {
			toSelectedPg(1, searchName);
		});
		previousPageLi.click(function() {
			toSelectedPg(pageNum - 1, searchName);
		});
	}
	let nextPageLi = $("<li class='page-item'></li>").append(
		$("<a class='page-link'></a>").append("&raquo;").attr("href", "#"));
	let lastPageLi = $("<li class='page-item'></li>").append(
		$("<a class='page-link'></a>").append("最後のページへ").attr("href", "#"));
	if (!result.extend.pageInfo.hasNextPage) {
		nextPageLi.addClass("disabled");
		lastPageLi.addClass("disabled");
	} else {
		lastPageLi.addClass("success");
		nextPageLi.click(function() {
			toSelectedPg(pageNum + 1, searchName);
		});
		lastPageLi.click(function() {
			toSelectedPg(totalPages, searchName);
		});
	}
	ul.append(firstPageLi).append(previousPageLi);
	$.each(result.extend.pageInfo.navigatePageNums, (index, item) => {
		let numsLi = $("<li class='page-item'></li>").append(
			$("<a class='page-link'></a>").append(item).attr("href", "#"));
		if (pageNum === item) {
			numsLi.attr("href", "#").addClass("active");
		}
		numsLi.click(function() {
			toSelectedPg(item, searchName);
		});
		ul.append(numsLi);
	});
	ul.append(nextPageLi).append(lastPageLi);
	let navElement = $("<nav></nav>").append(ul);
	navElement.appendTo("#pageNavi");
}
$("#cityAddModalBtn").on('click', function() {
	formReset("#cityAddModal form");
	getContinents("#continentInput");
	getNations($("#nationInput"), 'Africa');
	$("#cityAddModal").modal({
		backdrop: 'static'
	});
});
function getContinents(element) {
	$(element).empty();
	$.ajax({
		url: '/jpasample/continents',
		type: 'GET',
		dataType: 'json',
		success: function(result) {
			$.each(result.extend.continentList, function() {
				let optionElement = $("<option></option>").append(this)
					.attr("value", this);
				optionElement.appendTo(element);
			});
		}
	});
}
$("#continentInput").on('change', function() {
	let continentVal = $("#continentInput option:selected").val();
	getNations($("#nationInput"), continentVal);
});
function getNations(element, continentVal) {
	$(element).empty();
	$.ajax({
		url: '/jpasample/nations',
		data: 'continentVal=' + continentVal,
		type: 'GET',
		dataType: 'json',
		success: function(result) {
			$.each(result.extend.nationList, function() {
				let optionElement = $("<option></option>").append(this).attr("value", this);
				optionElement.appendTo(element);
			});
		}
	});
}
$("#nameInput").change(function() {
	let cityName = this.value;
	$.ajax({
		url: '/jpasample/check',
		data: 'cityName=' + cityName,
		type: 'GET',
		success: function(result) {
			if (result.code === 200) {
				showValidationMsg("#nameInput", "success", "");
				$("#infoSaveBtn").attr("ajax-va", "success");
			} else {
				showValidationMsg("#nameInput", "error", result.extend.validatedMsg);
				$("#infoSaveBtn").attr("ajax-va", "error");
			}
		}
	});
});
$("#infoSaveBtn").on('click', function() {
	let inputDistrict = $("#districtInput").val().trim();
	let inputPopulation = $("#populationInput").val().trim();
	let regularDistrict = /^[a-zA-Z-\s]{2,33}$/;
	let regularPopulation = /^\d{4,18}$/;
	if ($(this).attr("ajax-va") === "error") {
		return false;
	} else if (!regularDistrict.test(inputDistrict)
		&& !regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtInput", "error", "入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
		showValidationMsg("#populationInput", "error", "入力した人口数量が4桁から18桁までの数字にしなければなりません。");
		return false;
	} else if (!regularDistrict.test(inputDistrict)
		&& regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtInput", "error", "入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
		showValidationMsg("#populationInput", "success", "√");
		return false;
	} else if (regularDistrict.test(inputDistrict)
		&& !regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtInput", "success", "√");
		showValidationMsg("#populationInput", "error", "入力した人口数量が4桁から18桁までの数字にしなければなりません。");
		return false;
	} else {
		showValidationMsg("#districtInput", "success", "√");
		showValidationMsg("#populationInput", "success", "√");
		// Send an ajax request to commit save options.
		$.ajax({
			url: '/jpasample/city',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify({
				'name': $("#nameInput").val().trim(),
				'continent': $("#continentInput option:selected").val(),
				'nation': $("#nationInput option:selected").val(),
				'district': inputDistrict,
				'population': inputPopulation
			}),
			contentType: 'application/json;charset=UTF-8',
			success: function(result) {
				if (result.code === 200) {
					$("#cityAddModal").modal('hide');
					window.location
						.replace('/jpasample/city?pageNum=' + totalPages + '&keyword=');
				} else if (undefined !== result.extend.errorFields.name) {
					showValidationMsg("#nameInput", "error", result.extend.errorFields.name);
				}
			}
		});
	}
});
$(document).on('click', '.edit_btn', function() {
	let id = $(this).attr("id");
	formReset("#cityEditModal form");
	getCityInfo(id);
	$("#infoChangeBtn").attr("editId", id);
	$("#cityEditModal").modal({
		backdrop: 'static'
	});
});
function getCityInfo(id) {
	$.ajax({
		url: '/jpasample/city/' + id,
		type: 'GET',
		dataType: 'json',
		success: function(result) {
			let cityData = result.extend.citySelected;
			$("#cityEdit").text(cityData.name);
			$("#continentEdit").text(cityData.continent);
			$("#languageEdit").text(cityData.language);
			$("#districtEdit").val(cityData.district);
			$("#populationEdit").val(cityData.population);
			getNationsById("#nationEdit", id);
		}
	});
}
function getNationsById(element, id) {
	$(element).empty();
	$.ajax({
		url: '/jpasample/nations/' + id,
		type: 'GET',
		dataType: 'json',
		success: function(result) {
			$.each(result.extend.nationsWithName, function() {
				let optionElement = $("<option></option>").append(this).attr("value", this);
				optionElement.appendTo(element);
			});
		}
	});
}
$("#nationEdit").on('change', function() {
	let nationVal = $("#nationEdit option:selected").val();
	getLanguages($("#languageEdit"), nationVal);
});
function getLanguages(element, nationVal) {
	$(element).empty();
	$.ajax({
		url: '/jpasample/languages',
		data: 'nationVal=' + nationVal,
		type: 'GET',
		dataType: 'json',
		success: function(result) {
			$("#languageEdit").text(result.extend.languages);
		}
	});
}
$("#infoChangeBtn").on('click', function() {
	let editId = $(this).attr("editId");
	let inputDistrict = $("#districtEdit").val().trim();
	let inputPopulation = $("#populationEdit").val().trim();
	let regularDistrict = /^[a-zA-Z-\s]{2,33}$/;
	let regularPopulation = /^\d{4,18}$/;
	if (!regularDistrict.test(inputDistrict)
		&& !regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtEdit", "error", "入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
		showValidationMsg("#populationEdit", "error", "入力した人口数量が4桁から18桁までの数字にしなければなりません。");
		return false;
	}
	if (!regularDistrict.test(inputDistrict)
		&& regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtEdit", "error", "入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
		showValidationMsg("#populationEdit", "success", "√");
		return false;
	}
	if (regularDistrict.test(inputDistrict)
		&& !regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtEdit", "success", "√");
		showValidationMsg("#populationEdit", "error", "入力した人口数量が4桁から18桁までの数字にしなければなりません。");
		return false;
	}
	showValidationMsg("#districtEdit", "success", "√");
	showValidationMsg("#populationEdit", "success", "√");
	$.ajax({
		url: '/jpasample/city/' + editId,
		type: 'PUT',
		dataType: 'json',
		data: JSON.stringify({
			'id': editId,
			'name': $("#cityEdit").text(),
			'continent': $("#continentEdit").text(),
			'nation': $("#nationEdit option:selected").val(),
			'district': inputDistrict,
			'population': inputPopulation
		}),
		contentType: 'application/json;charset=UTF-8',
		success: function() {
			$("#cityEditModal").modal('hide');
			window.location.replace('/jpasample/city?pageNum=' + pageNum + '&keyword=');
		}
	});
});
$(document).on('click', '.delete_btn', function() {
	let cityName = $(this).attr("id");
	let cityId = $(this).attr("deleteId");
	if (confirm("この" + cityName + "という都市の情報を削除する、よろしいでしょうか。")) {
		$.ajax({
			url: '/jpasample/city/' + cityId,
			type: 'DELETE',
			dataType: 'json',
			success: function(result) {
				window.location.replace('/jpasample/city?pageNum=' + pageNum + '&keyword=');
			}
		});
	}
});
function formReset(element) {
	$(element)[0].reset();
	$(element).find("*").removeClass("has-error has-success");
	$(element).find(".help-block").text("");
}
function showValidationMsg(element, status, msg) {
	$(element).parent().removeClass("has-success has-error");
	$(element).next("span").text("");
	if (status === "success") {
		$(element).parent().addClass("has-success");
		$(element).next("span").text(msg);
	} else if (status === "error") {
		$(element).parent().addClass("has-error");
		$(element).next("span").text(msg);
	}
}