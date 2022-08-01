## Weather App

* App 구성    
  * RecyclerView - 중첩 RecyclerView 구현
  * ViewBinding
  * Retrofit2
  * Glide

* 문제점 및 해결해야할 사항
  * DataBinding + ViewModel + LiveData 로 구현하여 api data 가져올 시 라이브 데이터 사용하여 UI 업데이트 하려 했으나 구현못함
  * 중첩 RecyclerView에서 LiveData 및 DataBinding 구현하는데 어려움이 있었음
  * Seoul - London - Chicago 순으로 보여주기 위해 좋지 못한 방식으로 구현 됨 -> LiveData 로 해결할 수 있을 것 같음
  * 날씨 데이터가 시간별 데이터로 들어오는데 하루에 하나씩 보여줘야하는 로직 구성 안됨