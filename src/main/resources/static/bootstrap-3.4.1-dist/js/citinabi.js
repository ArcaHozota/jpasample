let pageNum = /*[[${pageInfo.getNumber()+1}]]*/{};
let pageTotal = /*[[${pageInfo.getTotalPages()}]]*/{};
$("#searchBtn").on(
		'click',
		function() {
			let keyword = $("#keywordInput").val().trim().toString();
			$.ajax({
				url : '/ssmcrud/city?pageNum=' + pageNum + '&keyword='
						+ keyword,
				type : 'GET',
				dataType : 'html',
				success : function() {
					window.location.replace('/ssmcrud/city?pageNum=1&keyword='
							+ keyword);
				}
			});
		});
$("#cityAddModalBtn").on('click', function() {
	formReset("#cityAddModal form");
	getContinents("#continentInput");
	$("#cityAddModal").modal({
		backdrop : 'static'
	});
});
function getContinents(element) {
	$(element).empty();
	$.ajax({
		url : '/ssmcrud/continents',
		type : 'GET',
		dataType : 'json',
		success : function(result) {
			$.each(result.extend.continentList, function() {
				let optionElement = $("<option></option>").append(this).attr(
						"value", this);
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
		url : '/ssmcrud/nations',
		data : 'continentVal=' + continentVal,
		type : 'GET',
		dataType : 'json',
		success : function(result) {
			$.each(result.extend.nationList, function() {
				let optionElement = $("<option></option>").append(this).attr(
						"value", this);
				optionElement.appendTo(element);
			});
		}
	});
}
$("#nameInput").change(
		function() {
			let cityName = this.value;
			$.ajax({
				url : '/ssmcrud/check',
				data : 'cityName=' + cityName,
				type : 'GET',
				success : function(result) {
					if (result.code === 200) {
						showValidationMsg("#nameInput", "success", "");
						$("#infoSaveBtn").attr("ajax-va", "success");
					} else {
						showValidationMsg("#nameInput", "error",
								result.extend.validatedMsg);
						$("#infoSaveBtn").attr("ajax-va", "error");
					}
				}
			});
		});
$("#infoSaveBtn")
		.on(
				'click',
				function() {
					let inputDistrict = $("#districtInput").val().trim();
					let inputPopulation = $("#populationInput").val().trim();
					let regularDistrict = /^[a-zA-Z-\s]{2,33}$/;
					let regularPopulation = /^[0-9]{4,18}$/;
					if ($(this).attr("ajax-va") === "error") {
						return false;
					} else if (!regularDistrict.test(inputDistrict)
							&& !regularPopulation.test(inputPopulation)) {
						showValidationMsg("#districtInput", "error",
								"入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
						showValidationMsg("#populationInput", "error",
								"入力した人口数量が4桁から18桁までの数字にしなければなりません。");
						return false;
					} else if (!regularDistrict.test(inputDistrict)
							&& regularPopulation.test(inputPopulation)) {
						showValidationMsg("#districtInput", "error",
								"入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
						showValidationMsg("#populationInput", "success", "√");
						return false;
					} else if (regularDistrict.test(inputDistrict)
							&& !regularPopulation.test(inputPopulation)) {
						showValidationMsg("#districtInput", "success", "√");
						showValidationMsg("#populationInput", "error",
								"入力した人口数量が4桁から18桁までの数字にしなければなりません。");
						return false;
					} else {
						showValidationMsg("#districtInput", "success", "√");
						showValidationMsg("#populationInput", "success", "√");
						// Send an ajax request to commit save options.
						$
								.ajax({
									url : '/ssmcrud/city',
									type : 'POST',
									dataType : 'json',
									data : JSON
											.stringify({
												'name' : $("#nameInput").val()
														.trim(),
												'continent' : $(
														"#continentInput option:selected")
														.val(),
												'nation' : $(
														"#nationInput option:selected")
														.val(),
												'district' : inputDistrict,
												'population' : inputPopulation
											}),
									contentType : 'application/json;charset=UTF-8',
									success : function(result) {
										if (result.code === 200) {
											$("#cityAddModal").modal('hide');
											window.location
													.replace('/ssmcrud/city?pageNum='
															+ pageTotal
															+ '&keyword=');
										} else if (undefined !== result.extend.errorFields.name) {
											showValidationMsg(
													"#nameInput",
													"error",
													result.extend.errorFields.name);
										}
									}
								});
					}
				});
$(document).on("click", ".edit_btn", function() {
	let id = $(this).attr("id");
	formReset("#cityEditModal form");
	getCityInfo(id);
	$("#infoChangeBtn").attr("editId", id);
	$("#cityEditModal").modal({
		backdrop : 'static'
	});
});
function getCityInfo(id) {
	$.ajax({
		url : '/ssmcrud/city/' + id,
		type : 'GET',
		dataType : 'json',
		success : function(result) {
			let cityData = result.extend.citySelected;
			$("#cityEdit").text(cityData.name);
			$("#continentEdit").text(cityData.continent);
			$("#districtEdit").val(cityData.district);
			$("#populationEdit").val(cityData.population);
			getNationsById("#nationEdit", id);
		}
	});
}
function getNationsById(element, id) {
	$(element).empty();
	$.ajax({
		url : '/ssmcrud/nations/' + id,
		type : 'GET',
		dataType : 'json',
		success : function(result) {
			$.each(result.extend.nationsWithName, function() {
				let optionElement = $("<option></option>").append(this).attr(
						"value", this);
				optionElement.appendTo(element);
			});
		}
	});
}
$("#infoChangeBtn").on(
		'click',
		function() {
			let editId = $(this).attr("editId");
			let inputDistrict = $("#districtEdit").val().trim();
			let inputPopulation = $("#populationEdit").val().trim();
			let regularDistrict = /^[a-zA-Z-\s]{2,33}$/;
			let regularPopulation = /^[0-9]{4,18}$/;
			if (!regularDistrict.test(inputDistrict)
					&& !regularPopulation.test(inputPopulation)) {
				showValidationMsg("#districtEdit", "error",
						"入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
				showValidationMsg("#populationEdit", "error",
						"入力した人口数量が4桁から18桁までの数字にしなければなりません。");
				return false;
			}
			if (!regularDistrict.test(inputDistrict)
					&& regularPopulation.test(inputPopulation)) {
				showValidationMsg("#districtEdit", "error",
						"入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
				showValidationMsg("#populationEdit", "success", "√");
				return false;
			}
			if (regularDistrict.test(inputDistrict)
					&& !regularPopulation.test(inputPopulation)) {
				showValidationMsg("#districtEdit", "success", "√");
				showValidationMsg("#populationEdit", "error",
						"入力した人口数量が4桁から18桁までの数字にしなければなりません。");
				return false;
			}
			showValidationMsg("#districtEdit", "success", "√");
			showValidationMsg("#populationEdit", "success", "√");
			$.ajax({
				url : '/ssmcrud/city/' + editId,
				type : 'PUT',
				dataType : 'json',
				data : JSON.stringify({
					'id' : editId,
					'name' : $("#cityEdit").text(),
					'continent' : $("#continentEdit").text(),
					'nation' : $("#nationEdit option:selected").val(),
					'district' : inputDistrict,
					'population' : inputPopulation
				}),
				contentType : 'application/json;charset=UTF-8',
				success : function() {
					$("#cityEditModal").modal('hide');
					window.location.replace('/ssmcrud/city?pageNum=' + pageNum
							+ '&keyword=');
				}
			});
		});
$(document).on(
		"click",
		".delete_btn",
		function() {
			let cityName = $(this).attr("id");
			let cityId = $(this).attr("deleteId");
			if (confirm("この" + cityName + "という都市の情報を削除する、よろしいでしょうか。")) {
				$.ajax({
					url : '/ssmcrud/city/' + cityId,
					type : 'DELETE',
					dataType : 'json',
					success : function(result) {
						window.location.replace('/ssmcrud/city?pageNum='
								+ pageNum + '&keyword=');
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