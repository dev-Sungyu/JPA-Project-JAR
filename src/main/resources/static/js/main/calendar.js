const Calendar = tui.Calendar;
const container = document.getElementById('calendar');
const options = {
defaultView: 'month',
month : {
      dayNames : ['Sun', 'Mon', 'Tus', 'Wen', 'Thr', 'Fri', 'Sat'],
      isAlways6Weeks: false,
      startDayOfWeek: 1,
},
timezone: {
  zones: [
    {
      timezoneName: 'Asia/Seoul',
      displayLabel: 'Seoul',
    },
  ],
},
theme: {
        month: {
          dayName: {
            borderLeft: 'none',
            backgroundColor: '#36f',
          },
          moreView: {
            border: '1px solid grey',
            boxShadow: '0 2px 6px 0 grey',
            backgroundColor: 'white',
            width: 320,
            height: 200,
          },
          gridCell: {
            footerHeight: 31,
          },
        },
      },
calendars: [
  {
    id: 'cal0',
    name: '관리자',
    backgroundColor: '#03bd9e',
  },
  {
    id: 'cal1',
    name: '관리자',
    backgroundColor: '#03bd9e',
  },
  {
    id: 'cal2',
    name: '관리자',
    backgroundColor: '#36f',
  },
  {
    id: 'cal3',
    name: '관리자',
    backgroundColor: 'orange',
  },
  {
    id: 'cal4',
    name: '관리자',
    backgroundColor: 'yellow',
  },
  {
    id: 'cal5',
    name: '관리자',
    backgroundColor: '#e1e1e1',
  },
  {
    id: 'cal6',
    name: '관리자',
    backgroundColor: '#e1e1e1',
  },
  {
    id: 'cal7',
    name: '관리자',
    backgroundColor: '#e1e1e1',
  },
  {
    id: 'cal8',
    name: '관리자',
    backgroundColor: '#e1e1e1',
  },
  {
    id: 'cal9',
    name: '관리자',
    backgroundColor: '#e1e1e1',
  },
],
};

const calendar = new Calendar(container, options);
const month = calendar.getDate().getMonth() + 1;
const year = calendar.getDate().getFullYear();

calendar.clearGridSelections();

let datas = [
    {
    id: '1',
    calendarId: 'cal2',
    title: '축구',
    start: '2023-04-09',
    end: '2023-04-10',
    isAllday: true,
    category: 'allday',
    },
    {
    id: '2',
    calendarId: 'cal1',
    title: '플러깅',
    start: '2023-04-16',
    end: '2023-04-17',
    isAllday: true,
    category: 'allday',
    },
    {
    id: '3',
    calendarId: 'cal3',
    title: '봉사활동',
    start: '2023-04-20',
    end: '2023-04-21',
    isAllday: true,
    category: 'allday',
    },
    {
    id: '4',
    calendarId: 'cal4',
    title: '먹고싶은 거 먹기',
    start: '2023-04-28',
    end: '2023-05-01',
    isAllday: true,
    category: 'allday',
    },
    {
    id: '5',
    calendarId: 'cal5',
    title: '농구',
    start: '2023-04-12',
    end: '2023-04-13',
    isAllday: true,
    category: 'allday',
    },
    {
    id: '6',
    calendarId: 'cal3',
    title: '남한산성 한 바퀴',
    start: '2023-04-05',
    end: '2023-04-08',
    isAllday: true,
    category: 'allday',
    },
    {
    id: '6',
    calendarId: 'cal3',
    title: '남한산성 한 바퀴',
    start: '2023-04-05',
    end: '2023-04-08',
    isAllday: true,
    category: 'allday',
    },
];

calendarDTOS.forEach((e, i) => {
  calendar.createEvents([
        e
  ]);
});



// 추가 이벤트
calendar.on('beforeCreateEvent', (calendarDTOS) => {
calendar.createEvents([
  {
    ...calendarDTOS,
  },
]);
});


// ==================================================== 캘린더 버튼
var currentDate = calendar.getDate();

$(".year").text(currentDate.getFullYear()+ "년");
$(".month").text(currentDate.getMonth() + 1 + "월");

$("#calender-prev").click(() => {
  currentDate = calendar.getDate();

  var prevDate = new Date(currentDate.getFullYear(), currentDate.getMonth() - 1, 1);
  var prevYear = prevDate.getFullYear();
  var prevMonthIndex = prevDate.getMonth();

  $(".year").text(prevYear+ "년");
  $(".month").text(prevMonthIndex + 1 + "월");
  
  calendar.prev();
});

$("#calender-next").click(() => {
  currentDate = calendar.getDate();
  
  var nextDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 1);
  var nextMonthIndex = nextDate.getMonth();
  var nextYear = nextDate.getFullYear();

  $(".year").text(nextYear+ "년");
  $(".month").text(nextMonthIndex + 1 + "월");
  
  calendar.next();
})

$("#today").click(() => {
  $(".year").text(year+ "년");
  $(".month").text(month + "월");

  calendar.today();
});