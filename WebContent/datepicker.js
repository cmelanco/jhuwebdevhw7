function setMonths()
{
	var months = document.hikepicker.months;
	months.options.length = 0;
	months.options[months.options.length] = new Option('Jan','1');
	months.options[months.options.length] = new Option('Feb','2');
	months.options[months.options.length] = new Option('Mar','3');
	months.options[months.options.length] = new Option('Apr','4');
	months.options[months.options.length] = new Option('May','5');
	months.options[months.options.length] = new Option('Jun','6');
	months.options[months.options.length] = new Option('Jul','7');
	months.options[months.options.length] = new Option('Aug','8');
	months.options[months.options.length] = new Option('Sept','9');
	months.options[months.options.length] = new Option('Oct','10');
	months.options[months.options.length] = new Option('Nov','11');
	months.options[months.options.length] = new Option('Dec','12');
}
function setYears()
{
	var years = document.hikepicker.years;
	years.options.length = 0;
	var i;
	for (i=2013; i<=2020;i++)
	{
		years.options[years.options.length] = new Option(i, i);
	}
}
function setDays(month, year)
{
	var monthDays = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

	var year = document.hikepicker.years.value;
	var month = document.hikepicker.months.value;
	var day = document.hikepicker.days.value;
	if (day == "")
	{
		day = 1;
	}
	var curDays = monthDays[month-1];
	if ((year % 4) == 0) {
		curDays++;
	} 
	
	var days = document.hikepicker.days;
	days.options.length = 0;
	var i;
	for (i=1; i<=curDays;i++)
	{
		days.options[days.options.length] = new Option(i, i);
	}
	
	if (day > curDays)
	{
		day = curDays;
	}
	days.options[day-1].selected=true;
}

function setDates()
{
	setMonths();
	setYears();
	setDays();
}