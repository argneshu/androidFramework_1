/* counts */
var totalTests, passedTests, failedTests, fatalTests, warningTests, errorTests, skippedTests, unknownTests;
var totalSteps, passedSteps, failedSteps, fatalSteps, warningSteps, errorSteps, infoSteps, skippedSteps, unknownSteps;

//script gets the src attribute based on ID of page's script element:
var requestURL = document.getElementById("myScript").getAttribute("src");

//next use substring() to get querystring part of src
var queryString = requestURL.substring(requestURL.indexOf("?") + 1, requestURL.length);
var fTest = parseInt(queryString);


/* global chart instance vars */
var testChart, stepChart;

function hideElement(el) {
	el.removeClass('displayed').addClass('hide');
}

function showElement(el) {
	el.addClass('displayed').removeClass('hide');
}

$(document).ready(function() {
	
	var elClassName="collection-item test displayed active";
	var elArray;
	if (!document.getElementsByClassName)
	{
		elArray= findElements(elClassName );
	}
	else
	{
		elArray = document.getElementsByClassName(elClassName);
	}
	for(var i = 0; i <elArray.length-1; i++) {
		if (elArray[i].getElementsByTagName('span')[0].textContent.indexOf(elArray[i+1].getElementsByTagName('span')[0].textContent) != -1) {
			if(elArray[i].getElementsByTagName('span')[1].textContent.indexOf("fail") != -1)
			{
				elArray[i].style.display = "none";
			}else if(elArray[i+1].getElementsByTagName('span')[1].textContent.indexOf("fail") != -1){
				elArray[i+1].style.display = "none";
			}
		}	
	}
	
	/* init */
	$('select').material_select();
	$('#refreshCharts').addClass('enabled').children('i').addClass('active');
	
	/* hide category toggle dropdown if no categories were added */
	if ($('#category-toggle > li').length <= 2) {
		$('a.category-toggle').addClass('hide');
	}
	
	/* [WINDOW] */
	$(window).scroll(function() {
		var scrollTop = $('.charts').is(':visible') ? 350 : 100;
		
		if ($(window).scrollTop() > scrollTop) {
			var margin = $('.charts').is(':visible') ? '-346px' : '-48px';
			$('.details-view').css('position', 'fixed').css('margin-top', margin);
		} 
		else {
			$('.details-view').removeAttr('style').css('position', 'absolute');
		}
	});
	
	/* toggle side-nav */
	$('.menu-toggle').click(function() {
		$('.side-nav').toggleClass('active');
	});
	
	/* theme selector */
	$('.theme-selector').click(function() {
		$('body').toggleClass('dark');
	});
	
	/* enable dashboard checkbox [TOPNAV] */
	$('#enableDashboard').click(function() {
		var t = $(this);
		t.toggleClass('enabled').children('i').toggleClass('active');
		$('#dashboard-view').toggleClass('hide').children('div').toggleClass('hide').siblings('.charts').toggleClass('hide');
		t.hasClass('enabled') ? redrawCharts() : null;
	});
	
	/* enable dashboard checkbox [TOPNAV] */
	$('#refreshCharts').click(function() {
		$(this).toggleClass('enabled').children('i').toggleClass('active');
	});
	
	/* side-nav navigation [SIDE-NAV] */
	$('.analysis').click(function() {
		$('.container > .row, .nav-right > .test-view-only').addClass('hide');
		
		var el = $(this);
		var cls = el.children('a').prop('class');
		
		$('#' + cls).removeClass('hide');
		
		if (cls == 'test-view') { 
			$('.nav-right > .test-view-only').removeClass('hide');
			
			if ($('#enableDashboard').hasClass('enabled') && !$('#dashboard-view > .charts').is(':visible')) {
				$('#enableDashboard').click().prop('checked', true).addClass('enabled');
			}
		}
		else {
			$('#dashboard-view > div').removeClass('hide');
		}
		
		$('#slide-out > .analysis').removeClass('active');
		el.addClass('active');
	});
	
	/* test-dashboard settings [DASHBOARD] */
	$('#dashboard-view .test-count-setting').click(function() {
		$('#test-count-setting').openModal();
	});
	
	/* test count setting */
		/* init */
		$('#parentWithoutNodesAndNodes').click();
	$('#test-count-setting input').click(function() {
		$('#test-count-setting').removeClass('parentWithoutNodes parentWithoutNodesAndNodes childNodes');
		$('#test-count-setting').addClass($(this).prop('id'));
	});
	
	/* step-dashboard settings [DASHBOARD] */
	$('#dashboard-view .step-status-filter').click(function() {
		$('#step-status-filter').openModal();
	});
	
	/* refresh charts when chart setting is saved */
	$('.modal-footer').click(function() {
		redrawCharts();
	});
	
	/* check all checkboxes for step-dashboard filter to allow filtering the steps to be displayed [DASHBOARD] */
	$('#step-status-filter input').prop('checked', 'checked');
	$('#step-status-filter input').click(function() {
	   $('#step-status-filter').toggleClass($(this).prop('id').replace('step-dashboard-filter-', ''));
	});
	
	/* hide category menu if no categories exist  */
	if ($('.category').length == 0) {
		$('#slide-out > .analysis > .categories-view, .category-summary-view').addClass('hide').css('display', 'none');
	}
	
	/* view category info [CATEGORIES] */
	$('.category-item').click(function(evt) {		
		$('#cat-collection .category-item').removeClass('active');
		$('#cat-details-wrapper .cat-body').html('');
		
		var el = $(this).addClass('active').find('.cat-body').clone();
		$('#cat-details-wrapper .cat-name').text($(this).find('.category-name').text());
		$('#cat-details-wrapper .cat-container').append($(el));
	});
	$('.category-item').eq(0).click();
	
	/* category filter by status */
	$('#cat-details-wrapper').click(function(evt) {
		var t = $(evt.target);
		
		if (t.is('.category-link')) {
			var id = t.attr('extentid');
			findTestByNameId(t.text().trim(), id);
		}
		
		if (t.is('.filter, .icon')) {
			if (t.hasClass('icon')) {
				t = t.parent();
			}
			
			var wrap = $('#cat-details-wrapper');

			/* push effect */
			$('#cat-details-wrapper .filter').removeClass('active')
			t.addClass('active');
			
			wrap.find('tbody > tr').removeClass('hide');
			
			if (t.hasClass('pass')) {
				wrap.find('tbody > tr:not(.pass)').addClass('hide');
			}
			else if (t.hasClass('fail')) {
				wrap.find('tbody > tr:not(.fail)').addClass('hide');
			}
			else {
				wrap.find('tbody > tr.fail, tbody > tr.pass').addClass('hide');
			}
		}
	});
	
	/* view test info [TEST] */
	$('.test').click(function() {
		var t = $(this);

		$('#test-collection .test').removeClass('active');
		$('#test-details-wrapper .test-body').html('');
		
		var el = t.addClass('active').find('.test-body').clone();
		$('#test-details-wrapper .details-name').html(t.find('.test-name').html());
		$('#test-details-wrapper .details-container').append($(el));
		
		var collapsible = $('#test-details-wrapper .collapsible');
		if (collapsible.length > 0) {
			collapsible.collapsible({ accordion : true });
		}
	});
	$('.test').eq(0).click();
	
	/* toggle search */
	$('.mdi-action-search, .fa-search').click(function() {
		$(this).toggleClass('active');
		var s = $('.search > .input-field');
		s.animate({ width: s.css('width') == '0px' ? '240px' : '0px'}, 200);
	});
	
	/* filter tests by text in test and categories view */
	$.fn.dynamicTestSearch = function(id){ 
		var target = $(this);
		var searchBox = $(id);
		
		searchBox.off('keyup').on('keyup', function() {
			pattern = RegExp(searchBox.val(), 'gi');
			
			if (searchBox.val() == '') {
				target.removeClass('hide');
			}
			else {
				target.addClass('hide').each(function() {
					var t = $(this);
					if (pattern.test(t.html())) {
						t.removeClass('hide');
					}
				});
			}
		});
		
		return target;
	}
	$('#test-collection .test').dynamicTestSearch('#test-view #searchTests');
	$('#cat-collection .category-item').dynamicTestSearch('#categories-view #searchTests');
	
	/* if only header row is available for test, hide the table [TEST] */
	$('.table-results').filter(function() {
		return ($(this).find('tr').length == 1);
	}).hide(0);
	
	/* clicking a section on pie chart will automatically filter tests by status */
	$('#test-analysis').click(
		function(evt) {
			var label = testChart.getSegmentsAtEvent(evt)[0].label;
			
			$('#tests-toggle li').filter(
				function() {
					return ($(this).text() == label);
				}
			).click();
		}
	);
	
	/* clicking the category tag will automatically filter tests by category */
	$('#test-details-wrapper').click(function(evt) {
		var el = $(evt.target);
		
		if (el.hasClass('category')) {
			var label = el.text();
			
			$('#category-toggle a').filter(
				function() {
					return ($(this).text() == label);
				}
			).click();
		}
	});
	
	/* filter tests by status [TEST] */
	$('#tests-toggle li').click(function() {
		if ($(this).hasClass('clear')) {
			resetFilters();
			return;
		}
		
		var opt = $(this).text().toLowerCase();
		var cat = $('#category-toggle li.active').text().toLowerCase();

		$('#tests-toggle li').removeClass('active');
		$(this).addClass('active');
		$('.test, .node-list > li').addClass('hide').removeClass('displayed');
		
		if (cat != '') {
			$('.test').each(function() {
				var t = $(this);
				
				if (t.find('.category-assigned').hasClass(cat)) {
					if (t.hasClass(opt) || t.has('.test-node.' + opt).length > 0) {
						t.addClass('displayed').removeClass('hide');
					}
				}
			});
		} 
		else {
			$('.test:has(.test-node.' + opt + '), .test.' + opt + ', .node-list > li.' + opt).removeClass('hide').addClass('displayed');
		}
		
		$('#test-view .tests-toggle > i').addClass('active');
		$('#test-collection .test.displayed').eq(0).click();
		redrawCharts();
	});
	
	/* filter tests by category [TEST] */
	$('#category-toggle li').click(function() {
		if ($(this).hasClass('clear')) {
			resetFilters();
			return;
		}
		
		var opt = $(this).text().toLowerCase().replace(/\./g, '').replace(/\#/g, '');
		var status = $('#tests-toggle li.active').text().toLowerCase();
		
		$('#category-toggle li').removeClass('active');
		$(this).addClass('active');
		$('.test').addClass('hide').removeClass('displayed');
		
		if (status != '') {
			$('.test').each(function() {
				var t = $(this);
				
				if (t.find('.category-assigned').hasClass(opt)) {
					if (t.hasClass(status) || t.has('.test-node.' + status).length > 0) {
						t.addClass('displayed').removeClass('hide');
					}
				}
			});
		} 
		else {
			$('#test-collection .category-assigned.' + opt).each(function() {
				$(this).parents(':eq(1)').removeClass('hide').addClass('displayed');
			});
		}
		
		$('#test-view .category-toggle > i').addClass('active');
		$('.test.displayed').eq(0).click();
		redrawCharts();
	});
	
	/* clear filters button */
	$('#clear-filters').click(function() {
		resetFilters();
	});

	/* hide testrunner-logs view if no logs added [TESTRUNNER-LOGS] */
	if ($('#testrunner-logs-view .card-panel > p').length == 0) {
		$('.analysis > .testrunner-logs-view').addClass('hide');
	}
	
	/* reset test/category filters on document load */
	resetFilters();
});

/* action to perform when 'Clear Filters' option is selected [TEST] */
function resetFilters() {
	$('.dropdown-content, .dropdown-content li').removeClass('active');
	$('.test, .node-list > li').addClass('displayed').removeClass('hide');
	$('#test-view .tests-toggle > i, #test-view .category-toggle > i').removeClass('active');
	redrawCharts();
}

/* formats date in mm-dd-yyyy hh:mm:ss [UTIL] */
function formatDt(d) {
	return d.getFullYear() + '-' +
		('00' + (d.getMonth() + 1)).slice(-2) + '-' + 
		('00' + d.getDate()).slice(-2) + ' ' + 
		('00' + d.getHours()).slice(-2) + ':' + 
		('00' + d.getMinutes()).slice(-2) + ':' + 
		('00' + d.getSeconds()).slice(-2);
}

/* finds test by its name and extentId  [UTIL] */
function findTestByNameId(name, id) {
	$('.test').each(function() {
		var t = $(this);
		
		if (t.find('.test-name').text().trim() == name && t.attr('extentid') == id) {
			$('.analysis > .test-view').click();
			
			$('html, body').animate({
				scrollTop: $('.details-name').offset().top
			}, 400);
			
			t.click();
			return;
		}
	});
}

/* refresh and redraw charts [DASHBOARD] */
function redrawCharts() {
	if (!$('#dashboard-view .charts').is(':visible') || !$('#refreshCharts').hasClass('enabled')) {
		return;
	}
	
	refreshData();
	
	testChart.segments[0].value = passedTests;
	testChart.segments[1].value = fTest;
	testChart.segments[2].value = fatalTests;
	testChart.segments[3].value = errorTests;
	testChart.segments[4].value = warningTests;
	testChart.segments[5].value = skippedTests;
	testChart.segments[6].value = unknownTests;
	stepChart.segments[0].value = passedSteps;
	stepChart.segments[1].value = infoSteps;
	stepChart.segments[2].value = failedSteps;
	stepChart.segments[3].value = fatalSteps;
	stepChart.segments[4].value = errorSteps;
	stepChart.segments[5].value = warningSteps;
	stepChart.segments[6].value = skippedSteps;
	stepChart.segments[7].value = unknownSteps;
	
	$('#test-analysis, #step-analysis').html('');
	$('ul.doughnut-legend').html('');
	
	testsChart();
	stepsChart();
	
	$('ul.doughnut-legend').addClass('right');
}

/* update data for dashboard [DASHBOARD] */
function refreshData() {
	var el = $('#test-count-setting');
	
	totalTests = $('.test:not(:has(.test-node)), .test-node').length;
	passedTests = $('.test.displayed .node-list > li.pass.displayed, .test.displayed.pass:not(.hasChildren)').length;
	//failedTests = $('.test.displayed .node-list > li.fail.displayed, .test.displayed.fail:not(.hasChildren)').length;
	fatalTests = $('.test.displayed .node-list > li.fatal.displayed, .test.displayed.fatal:not(.hasChildren)').length;
	warningTests = $('.test.displayed .node-list > li.warning.displayed, .test.displayed.warning:not(.hasChildren)').length;
	errorTests = $('.test.displayed .node-list > li.error.displayed, .test.displayed.error:not(.hasChildren)').length;
	skippedTests = $('.test.displayed .node-list > li.skip.displayed, .test.displayed.skip:not(.hasChildren)').length;
	unknownTests = $('.test.displayed .node-list > li.unknown.displayed, .test.displayed.unknown:not(.hasChildren)').length;
	
	if (el.hasClass('parentWithoutNodes')) {
		totalTests = $('.test.displayed').length;
		passedTests = $('.test.displayed.pass').length;
		//failedTests = $('.test.displayed.fail').length;
		fatalTests = $('.test.displayed.fatal').length;
		warningTests = $('.test.displayed.warning').length;
		errorTests = $('.test.displayed.error').length;
		skippedTests = $('.test.displayed.skip').length;
		unknownTests = $('.test.displayed.unknown').length;
	}
	else if (el.hasClass('childNodes')) {
		totalTests = $('.test-node').length;
		passedTests = $('.test.displayed .node-list > li.pass.displayed').length;
		//failedTests = $('.test.displayed .node-list > li.fail.displayed').length;
		fatalTests = $('.test.displayed .node-list > li.fatal.displayed').length;
		warningTests = $('.test.displayed .node-list > li.warning.displayed').length;
		errorTests = $('.test.displayed .node-list > li.error.displayed').length;
		skippedTests = $('.test.displayed .node-list > li.skip.displayed').length;
		unknownTests = $('.test.displayed .node-list > li.unknown.displayed').length;
	}
	
	totalSteps = $('#test-collection td.status').length;
	passedSteps = $('#test-collection td.status.pass').length;
	failedSteps = $('#test-collection td.status.fail').length;
	fatalSteps = $('#test-collection td.status.fatal').length;
	warningSteps = $('#test-collection td.status.warning').length;
	errorSteps = $('#test-collection td.status.error').length;
	infoSteps = $('#test-collection td.status.info').length;
	skippedSteps = $('#test-collection td.status.skip').length;
	unknownSteps = $('#test-collection td.status.unknown').length;
	
	$('.t-pass-count').text(passedTests);
	
	$('.t-fail-count').text(fTest + fatalTests);
	$('.t-warning-count').text(warningTests);
	$('.t-fatal-count').text(fatalTests);
	$('.t-error-count').text(errorTests);
	
	$('.t-skipped-count').text(skippedTests);
	$('.t-others-count').text(warningTests + errorTests + skippedTests + unknownTests);
	
	var percentage = Math.round((passedTests * 100) / (passedTests + fTest + fatalTests + warningTests + errorTests + unknownTests + skippedTests)) + '%';
	$('.pass-percentage.panel-lead').text(percentage);
	$('#dashboard-view .determinate').attr('style', 'width:' + percentage);
	
	if ($('#step-status-filter').hasClass('pass')) { passedSteps = 0; }
	if ($('#step-status-filter').hasClass('fail')) { failedSteps = 0; }
	if ($('#step-status-filter').hasClass('fatal')) { fatalSteps = 0; }
	if ($('#step-status-filter').hasClass('warning')) { warningSteps = 0; }
	if ($('#step-status-filter').hasClass('error')) { errorSteps = 0; }
	if ($('#step-status-filter').hasClass('info')) { infoSteps = 0; }
	if ($('#step-status-filter').hasClass('skip')){ skippedSteps = 0; }
	if ($('#step-status-filter').hasClass('unknown')) { unknownSteps = 0; }
	
	$('.s-pass-count').text(passedSteps);
	$('.s-fail-count').text(fTest + fatalSteps);
	$('.s-warning-count').text(warningSteps);
	$('.s-error-count').text(errorSteps);
	$('.s-skipped-count').text(skippedSteps);
	$('.s-others-count').text(warningSteps + errorSteps + skippedSteps + unknownSteps + infoSteps);
	$('.total-tests > .panel-lead').text(passedTests+fTest);
	$('.total-steps > .panel-lead').text(totalSteps);
}

/* dashboard chart options [DASHBOARD] */
var options = {
	segmentShowStroke : false, 
	percentageInnerCutout : 55, 
	animationSteps : 1,
	legendTemplate : '<ul class=\'<%=name.toLowerCase()%>-legend\'><% for (var i=0; i<segments.length; i++){%><li><span style=\'background-color:<%=segments[i].fillColor%>\'></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>'
};

/* tests view chart [DASHBOARD] */
function testsChart() {
	var data = [
		{ value: passedTests, color: '#00af00', highlight: '#32bf32', label: 'Pass' },
		{ value: fTest, color:'#F7464A', highlight: '#FF5A5E', label: 'Fail' },
		{ value: fatalTests, color:'#8b0000', highlight: '#a23232', label: 'Fatal' },
		{ value: errorTests, color:'#ff6347', highlight: '#ff826b', label: 'Error' },
		{ value: warningTests, color: '#FDB45C', highlight: '#FFC870', label: 'Warning' },
		{ value: skippedTests, color: '#1e90ff', highlight: '#4aa6ff', label: 'Skip' },
		{ value: unknownTests, color: '#222', highlight: '#444', label: 'Unknown' }
	];
		
	var ctx = $('#test-analysis').get(0).getContext('2d');
	testChart = new Chart(ctx).Doughnut(data, options);
	drawLegend(testChart, 'test-analysis');
}
  
/* steps view chart [DASHBOARD] */
function stepsChart() {
	var data = [
		{ value: passedSteps, color: '#00af00', highlight: '#32bf32', label: 'Pass' },
		{ value: infoSteps, color: '#46BFBD', highlight: '#5AD3D1', label: 'Info' },
		{ value: failedSteps, color:'#F7464A', highlight: '#FF5A5E', label: 'Fail' },
		{ value: fatalSteps, color:'#8b0000', highlight: '#a23232', label: 'Fatal' },
		{ value: errorSteps, color:'#ff6347', highlight: '#ff826b', label: 'Error' },
		{ value: warningSteps, color: '#FDB45C', highlight: '#FFC870', label: 'Warning' },
		{ value: skippedSteps, color: '#1e90ff', highlight: '#4aa6ff', label: 'Skip' },
		{ value: unknownSteps, color: '#222', highlight: '#444', label: 'Unknown' }
	];
	
	var ctx = $('#step-analysis').get(0).getContext('2d');
	stepChart = new Chart(ctx).Doughnut(data, options);
	drawLegend(stepChart, 'step-analysis');
}

/* draw legend for test and step charts [DASHBOARD] */
function drawLegend(chart, id) {
	var helpers = Chart.helpers;
	var legendHolder = document.getElementById(id);
	legendHolder.innerHTML = chart.generateLegend();
	
	helpers.each(legendHolder.firstChild.childNodes, function(legendNode, index) {
		helpers.addEvent(legendNode, 'mouseover', function() {
			var activeSegment = chart.segments[index];
			activeSegment.save();
			activeSegment.fillColor = activeSegment.highlightColor;
			chart.showTooltip([activeSegment]);
			activeSegment.restore();
		});
	});
	
	Chart.helpers.addEvent(legendHolder.firstChild, 'mouseout', function() {
		chart.draw();
	});
	$('#' + id).after(legendHolder.firstChild);
}
  
testsChart(); stepsChart();
$('ul.doughnut-legend').addClass('right');
redrawCharts();
  
$('#dashboard-view').addClass('hide');
  