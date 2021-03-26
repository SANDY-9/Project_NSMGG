![image](https://user-images.githubusercontent.com/71866565/112585263-d523b080-8e3c-11eb-9d32-6e4e180cf214.png)
![image](https://user-images.githubusercontent.com/71866565/112585270-d654dd80-8e3c-11eb-8a0e-1150e4a469e1.png)
![image](https://user-images.githubusercontent.com/71866565/109919022-4fa85700-7cfb-11eb-85e2-5b283fb6ce73.png)
![image](https://user-images.githubusercontent.com/71866565/109917867-651c8180-7cf9-11eb-9af9-d388993bf6bc.png)

## Project_NSMGG (날씨머꼬)
날씨 어플리케이션

## 목표
- 우리가 만든 앱을 플레이스토어에 출시하는 것
- 많은 사람들이 다운 받을만 하도록 만들어야한다.
- 언제든지 제약없이 사용할 수 있어야 한다.
- 기존에 알고 있던 지식으로부터 한계를 벗어나 미래를 위해 공부해둬야 할 내용에 도전.
- 자바 <-> 코틀린 자유롭게 사용하는 것
- MVVM 구성을 도와주는 안드로이드 JetPack 라이브러리 활용

## 구현기능
- Background 가능 FCM, 내부 푸쉬 알림
- Room활용 앱 내 전국 좌표 데이터 저장
- 환경설정 ( 알림 시간 설정 및 허용 여부, 지역 즐겨찾기 관리 )
- 지역 검색기능 및 즐겨찾기 추가 기능
- 현 위치기반 현재 기상상태 표출, 주간 날씨 표출
- 현 위치 기반 현재(오늘) 상세 정보 제공 기능
- Feedback 소통 기능
- 다른 지역 현재(오늘), 주간 날씨 정보

## 설계 중점
- Github을 통한 원활한 코드 관리
- 실시간 데이터 처리
- 직관적이고 사용하기 쉬운 UI/UX
- 데이터 흐름에 따라 화면 구상
- 메인 서버 없이도 인터넷 연결만으로 제약 없이 어플 사용
- MVVM 구성을 도와주는 안드로이드 JetPack 라이브러리 사용

## Development environment

| 종류 | 이름 |
| ------ | ------ |
| 개발 툴 | Android Studio, firebase, Postman, Open API(공공데이터 포털(기상청)) |
| 버전 관리 | GitHub |
| 데이터베이스 | Room |
| 언어 | Kotlin, Sql |

## 역할
### [SANDY-9 (feature_SunmiWork)](https://github.com/SANDY-9) ( 프로젝트 총괄 )
- 앱 디자인 총괄
- MVVM 패턴 설계
- navigation 설계
- Rest Api

### [aoqnwnd (feature_Seung)](https://github.com/aoqnwnd)
- Database 관리
- 환경설정 담당
- FCM, Remote Config 담당
- 위치 서비스
- Rest Api
