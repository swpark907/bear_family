import React from "react";
import { Button } from "../common";

const HomeHistory = () => {
  const historyLists = [
    {
      date: "2022-01-01",
      title: "제목입니다",
      cost: "가격",
      classification: "수입",
      category: "카테고리",
      place: "장소",
    },
    {
      date: "2022-01-01",
      title: "제목입니다",
      cost: "가격",
      classification: "지출",
      category: "카테고리",
      place: "장소",
    },
  ];

  return (
    <div className="home__history">
      {/* 각 리스트마다 Link로 해당 상세내역으로 이동 로직 만들 예정 */}
      <ul className="history__container">
        {historyLists.map((list, index) => (
          <li className="history__list" key={index}>
            <div className="list__title">
              <p>{list.title}</p>
              <p>{list.category}</p>
            </div>
            <p>{list.place}</p>
            <div className="list__content">
              <p>{list.date}</p>
              <p
                className={
                  "list__cost" +
                  (list.classification === "수입" ? " income" : " expenditure")
                }
              >
                {list.classification === "수입" ? "+ " : "- "}
                {list.cost}
              </p>
            </div>
          </li>
        ))}
      </ul>
      <Button variant={"tertiary"}>더 보러가기</Button>
    </div>
  );
};
export default HomeHistory;
