import logo from './logo.svg';
import './App.scss';
import axios from 'axios';

function App() {
  READ();

  return (
    <div className="App">
      <h1>This is App</h1>

      <input type="text" name="username" placeholder="아이디"/>
      <input type="password" name="password" placeholder="비밀번호"/>
      <input type="text" name="email" placeholder="이메일"/>
      <input type="text" name="name" placeholder="닉네임"/>
      <button id="signon" >회원가입</button>

      <h3>token : <span id="token"></span></h3>

      <input type="text" name="username" placeholder="아이디"/>
      <input type="text" name="password" placeholder="비밀번호"/>
      <button id="authenticate" >로그인</button>
    </div>
  );
}

async function READ() {
  var payload;

  await axios.get("/restapi/user").then(
    (Response) => {
      payload = Response.data.payload;
    }
  );

  console.log(payload);
  
  return payload;
}

function Authenticate(username, password){
  var token = axios.post('/authenticate', {
      username,
      password
  })
}

function SignOn(){

}

export default App;