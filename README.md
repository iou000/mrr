# NT_회의실 예약 시스템

(주)엔티시스에서 인턴을 진행하면서 제작한 엔티시스 회의실 예약 시스템입니다.

<br/>

## 페이지별 기능

- ### 달력 (mrrCldr.jsp)

  ![달력(메인)](https://user-images.githubusercontent.com/68727627/147057056-2262a0e5-e60f-40c1-b82d-2943f3383d10.PNG)

  DB에 저장되어 있는 회의실 예약 일정을 달력으로 한눈에 보여줌. (FullCalendar 라이브러리 사용)

  달, 주, 일 별로 회의실 예약 일정을 확인할 수 있고, 달력에 있는 회의를 클릭하면 아래와 같은 해당 회의실 예약 페이지로 이동함.

  ![회의실 예약(선택)](https://user-images.githubusercontent.com/68727627/147056804-d99a7e32-c4ea-47b4-b99f-b6f9e1b830f4.PNG)

<br/>

- ### 회의실 예약 관리 게시판 (selectPageListMrrMgt.jsp)

  ![selectPageListMrrMgt](https://user-images.githubusercontent.com/68727627/147057111-00a0b4fb-0cc4-4f3f-bf4b-8facc2b6fea7.PNG)

  회의실 예약 일정을 게시판 형식으로 확인할 수 있고, 예약 시작일, 종료일, 검색어를 통해 회의를 검색할 수 있음.

<br/>

- ### 회의실 예약(등록폼) (insertFormMrrMgt.jsp)

  ![회의실 예약 (등록폼) PNG](https://user-images.githubusercontent.com/68727627/147058055-1736576b-af31-4b23-bea3-e7849c6c2176.png)
  ![예약시간중복확인 PNG](https://user-images.githubusercontent.com/68727627/147058057-79896c07-4cb8-4f44-a9a0-4fb506d0621d.png)

  회의실을 예약하기 위한 등록폼.

  예약일과 예약시간은 중복되지 않게 등록해줘야 하기 때문에 중복을 검사하는 기능이 있음.

  등록을 하기 전에 반드시 중복확인을 해야함.
  
<br/>

- ### 회의실 예약(수정폼) (updateFormMrrMgt.jsp)
  ![회의실 예약(수정폼) PNG](https://user-images.githubusercontent.com/68727627/147058387-e69dc461-9ed4-4357-a4fb-c334d8e51077.png)
  
  예약된 회의내용을 수정할 수 있음. 수정시에도 역시 중복확인을 거쳐야함.

<br/>

- ### 회의실 예약(삭제)
  ![회의실예약(삭제) PNG](https://user-images.githubusercontent.com/68727627/147058390-0e73bae6-53e7-4f42-bc4e-6505d28574b0.png)
  
  등록된 회의를 삭제할 수 있음.
