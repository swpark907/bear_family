import React, { useState } from "react";
import { ModalTemplate, Title } from "../common";
import MapContainer from "./MapContainer ";

const cards = [
  { id: 0, name: "식비" },
  { id: 1, name: "교통" },
  { id: 2, name: "쇼핑" },
  { id: 3, name: "생활" },
  { id: 4, name: "패션" },
  { id: 5, name: "치료" },
  { id: 6, name: "건강" },
  { id: 7, name: "금융" },
  { id: 8, name: "문화" },
  { id: 9, name: "여행" },
  { id: 10, name: "반려동물" },
  { id: 11, name: "기타지출" },
];

const CreateModal = () => {
  const [categoryIsSelected, setCategoryIsSelected] = useState(false);
  const [category, setCategory] = useState("category");
  const [ledger, setLedger] = useState({
    title: "",
    price: "",
    kind: null,
    category_id: null,
    payment: null,
    location: "",
    date: "",
    description: "",
  });

  const [categoryBtnClicked, setCategoryBtnClicked] = useState(false);
  const [locationBtnClicked, setLocationBtnClicked] = useState(false);
  const [InputText, setInputText] = useState("");
  const [Place, setPlace] = useState("");
  const onChange = (e) => {
    setInputText(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setPlace(InputText);
    setInputText("");
  };

  const changeHandler = (e, inputName, props) => {
    // let value = e.target.value
    // console.log(inputName)
    switch (inputName) {
      case "title":
        setLedger((state) => ({
          ...state,
          title: e.target.value,
        }));
        break;
      case "price":
        // console.log(e.target.value)
        setLedger((state) => ({
          ...state,
          price: e.target.value,
        }));
        break;
      case "kind":
        setLedger((state) => ({
          ...state,
          kind: props.value,
        }));
        break;
      case "payment":
        setLedger((state) => ({
          ...state,
          payment: props.value,
        }));
        break;
      case "description":
        setLedger((state) => ({
          ...state,
          description: e.target.value,
        }));
        break;
      default:
        console.log("inputChange");
    }
  };

  const Card = ({ idx, name }) => {
    return (
      <div
        className={`category-card color2`}
        onClick={() => {
          setLedger((state) => ({
            ...state,
            category_id: idx,
          }));
          setCategory(() => name);
          setCategoryBtnClicked(false);
        }}
      >
        <img
          src={require(`../../assets/category${idx}.png`)}
          alt="#"
          className="card-img"
        />
        <span>{name}</span>
      </div>
    );
  };

  // modalContainer fixed top/bottom/left/right 0
  return (
    <section>
      <h2>거래내역 생성</h2>
      <div>
        <Title variant={"secondary"}>제목</Title>
        <div>
          <input
            value={ledger.title}
            type="text"
            onChange={(e) => {
              changeHandler(e, "title");
            }}
          />
        </div>
      </div>
      {/* 금액 */}
      <div>
        <Title variant={"secondary"}>금액</Title>
        <div>
          <input
            value={ledger.price}
            type="text"
            onChange={(e) => {
              changeHandler(e, "price");
            }}
          />
          원
        </div>
      </div>

      {/* 분류 */}
      <div>
        <Title variant={"secondary"}>분류</Title>
        <div>
          <button
            className="tertiary"
            onClick={(e) => {
              changeHandler(e, "kind", { value: 0 });
            }}
          >
            지출
          </button>
          <button
            className="tertiary"
            onClick={(e) => {
              changeHandler(e, "kind", { value: 1 });
            }}
          >
            수입
          </button>
        </div>
      </div>

      {/* 카테고리 */}
      <div>
        <Title variant={"secondary"}>카테고리</Title>
        <div>
          <div
            className="category-btn"
            onClick={() => {
              setCategoryBtnClicked(true);
            }}
          >
            <span>{category}</span>
          </div>
        </div>
      </div>

      {/* <CategoryModal /> */}
      <ModalTemplate
        btnContent={"확인"}
        state={categoryBtnClicked}
        setState={setCategoryBtnClicked}
      >
        <div
          style={{
            maxWidth: 300,
            display: "flex",
            flexDirection: "row",
            flexWrap: "wrap",
            justifyContent: "space-around",
            alignItems: "space-between",
          }}
        >
          {cards.map((card, id) => (
            <Card idx={card.id} name={card.name} key={id} />
          ))}
        </div>
      </ModalTemplate>

      {/* 거래처 */}
      <div>
        <Title variant={"secondary"}>거래처</Title>
        <div style={{ display: "flex" }}>
          <div
            className="category-btn"
            onClick={() => {
              setLocationBtnClicked(true);
            }}
          >
            <span>거래처 이름</span>
            {/* <input type="text" placeholder="거래처 이름"/> */}
          </div>
          <button
            onClick={() => {
              setLocationBtnClicked(true);
            }}
          >
            찾아보기
          </button>
        </div>
        <form className="inputForm" onSubmit={handleSubmit}>
          {/* <input placeholder="검색어를 입력하세요" onChange={onChange} value={InputText} /> */}
          {/* <button type="submit">검색</button> */}
        </form>
      </div>
      {/* <LocationModal /> */}
      <ModalTemplate
        btnContent={""}
        state={locationBtnClicked}
        setState={setLocationBtnClicked}
      >
        {/* <div style={{
          maxWidth:300, display: 'flex', flexDirection: 'row', flexWrap: 'wrap',
          justifyContent: 'space-around', alignItems: 'space-between'
        }}>
        </div> */}

        <form className="inputForm" onSubmit={handleSubmit}>
          <input
            placeholder="검색어를 입력하세요"
            onChange={onChange}
            value={InputText}
          />
          <button type="submit">검색</button>
        </form>
        <MapContainer searchPlace={Place} />
      </ModalTemplate>

      {/* 결제수단 */}
      <div>
        <Title variant={"secondary"}>결제수단</Title>
        <div>
          <button
            onClick={(e) => {
              changeHandler(e, "payment", { value: 0 });
            }}
          >
            현금
          </button>
          <button
            onClick={(e) => {
              changeHandler(e, "payment", { value: 1 });
            }}
          >
            카드
          </button>
          <button
            onClick={(e) => {
              changeHandler(e, "payment", { value: 2 });
            }}
          >
            신용카드
          </button>
        </div>
      </div>

      {/* 날짜 */}
      <div>
        <Title variant={"secondary"}>날짜</Title>
        <span htmlFor="">0000-00-00</span>
      </div>

      {/* 메모 */}
      <div>
        <Title variant={"secondary"}>메모</Title>
        <textarea
          className="memo"
          id=""
          rows="10"
          placeholder="거래내역에 대한 간단한 설명을 적어주세요."
          onChange={(e) => {
            changeHandler(e, "description");
          }}
        />
      </div>

      <button>생성</button>
      <button
        onClick={() => {
          console.log(ledger);
        }}
      >
        현재state
      </button>
    </section>
  );
};

export default CreateModal;
