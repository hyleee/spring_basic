<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

  <style>
    .bgimg {
      height: 200px;
      object-fit: cover;
      object-position: 10% 50%;
    }

    .main-container {
      flex-direction: column;
    }
    
    .container {
      max-width: 50rem;
    }

    .carousel-control-next-tmp {
      right: -8vw;
    }

    .carousel-control-next-tmp > span {
      background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' fill='000'%3e%3cpath d='M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z'/%3e%3c/svg%3e");
    }
     

    .carousel-control-prev-tmp {
      left: -8vw;
    }

    .carousel-control-prev-tmp > span {
      background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' fill='000'%3e%3cpath d='M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z'/%3e%3c/svg%3e");
    }

    h4 {
      margin-top: 30px;
      font-size: 20px;
    }
    .mb-3{
    	margin-top: 30px;
    }
    
    #select{
    margin-right : 40px;   
    font-size: 20px;
    }
    #sd{
    margin: 40px 0px 0px 0px;
    }

  </style>


  </head>

<body>
   <%@include file="/header.jsp" %>

  <main class="d-flex main-container justify-contents-center">
    <div id="carousel-background" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="./img/the_gym_new.jpg" class="d-block w-100 bgimg" alt="..." height: 100>
        </div>
        <div class="carousel-item">
          <img src="./img/the_gym_new.jpg" class="d-block w-100 bgimg" alt="...">
        </div>
        <div class="carousel-item">
          <img src="./img/the_gym_new.jpg" class="d-block w-100 bgimg" alt="...">
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carousel-background" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carousel-background" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <section class="container">

    <div>
      <form>
        <div class="mb-3">
          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
            placeholder="운동 제목 검색">
        </div>
      </form>
    </div>

    <div>
      <span id = "select">
        최근 가장 많이 본 영상
      </span>
      <a href="main?action=list" 
        class="btn btn-secondary" style="background-color: #007bff;">
        목록
      </a>
      <div id="carousel-top-video" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div class="card-group">
              <div class="card">
                <img src="https://img.youtube.com/vi/7TLk7pscICk/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)</h5>
                </div>
              </div>
              <div class="card">
                <img src="https://img.youtube.com/vi/gMaB-fG4u4g/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]</h5>
                </div>
              </div>
              <div class="card">
                <img src="https://img.youtube.com/vi/swRNeYw1JkY/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">하루 15분! 전신 칼로리 불태우는 다이어트 운동</h5>
                </div>
              </div>
            </div>
          </div>

          <div class="carousel-item">
            <div class="card-group">
              <div class="card">
                <img src="https://img.youtube.com/vi/54tTYO-vU2E/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]</h5>
                </div>
              </div>
              <div class="card">
                <img src="https://img.youtube.com/vi/QqqZH3j_vH0/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">상체비만 다이어트 최고의 운동 [상체 핵매운맛]</h5>
                </div>
              </div>
              <div class="card">
                <img src="https://img.youtube.com/vi/tzN6ypk6Sps/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]</h5>
                </div>
              </div>
            </div>
          </div>

        </div>
        <button class="carousel-control-prev carousel-control-prev-tmp" type="button" data-bs-target="#carousel-top-video" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next carousel-control-next-tmp" type="button" data-bs-target="#carousel-top-video" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>

    <div>
      <!-- <button id="load-btn">load</button>
      <div id="test">

      </div> -->
      <div id = "sd">
      <span id = "select">
        운동 부위 선택
      </span>
        <a href="main?action=list&part=전신" 
        class="btn btn-secondary" style="background-color: #007bff;">
        전신
        </a>
        <a href="main?action=list&part=상체"
        class="btn btn-secondary" style="background-color: #007bff;">
        상체
        </a>
        <a href="main?action=list&part=하체"
        class="btn btn-secondary" style="background-color: #007bff;">
        하체
        </a>
        <a href="main?action=list&part=복부"
        class="btn btn-secondary" style="background-color: #007bff;">
        복부
        </a>
      </div>
      <div id="carousel-type-video" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div class="card-group">
              <div class="card">
                <img src="https://img.youtube.com/vi/7TLk7pscICk/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)</h5>
                </div>
              </div>
              <div class="card">
                <img src="https://img.youtube.com/vi/gMaB-fG4u4g/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]</h5>
                </div>
              </div>
              <div class="card">
                <img src="https://img.youtube.com/vi/swRNeYw1JkY/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">하루 15분! 전신 칼로리 불태우는 다이어트 운동</h5>
                </div>
              </div>
            </div>
          </div>

          <div class="carousel-item">
            <div class="card-group">
              <div class="card">
                <img src="https://img.youtube.com/vi/54tTYO-vU2E/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]</h5>
                </div>
              </div>
              <div class="card">
                <img src="https://img.youtube.com/vi/QqqZH3j_vH0/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">상체비만 다이어트 최고의 운동 [상체 핵매운맛]</h5>
                </div>
              </div>
              <div class="card">
                <img src="https://img.youtube.com/vi/tzN6ypk6Sps/mqdefault.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]</h5>
                </div>
              </div>
            </div>
          </div>

        </div>
        <button class="carousel-control-prev carousel-control-prev-tmp" type="button" data-bs-target="#carousel-type-video" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next carousel-control-next-tmp" type="button" data-bs-target="#carousel-type-video" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>

    </div>
  </section>

  </main>

  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

  <script>

    // var loadBtn = document.querySelector('button#load-btn')

    // loadBtn.addEventListener('click', function () {
    //   axios.get("http://127.0.0.1:5502/data/video.json")
    //     .then(d => {
    //       var testDiv = document.querySelector('div#test');

    //       d.data.forEach(youtubeTitle => {
    //         var childNode = document.createElement('div');
    //         childNode.innerText = youtubeTitle.title;

    //         testDiv.appendChild(childNode)
    //       })
    //     })
    // })

    axios.get("http://127.0.0.1:5502/data/video.json")
      .then(d => {
        var testDiv = document.querySelector('card img');

        d.data.forEach(youtubeTitle => {
          var childNode = document.createElement('img');
          childNode.setAttribute(src, "1") = youtubeTitle.title;

          testDiv.appendChild(childNode)


        })
      })

  </script>
</body>

</html>