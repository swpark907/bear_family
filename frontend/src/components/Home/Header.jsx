import React from "react";
import { Title } from "../common";

const Header = () => {
  return (
    <div className="home__header">
      <Title variant="secondary" className={"header__title"}>
        꼼꼼
      </Title>
      <span className="header__config">설정 아이콘</span>
    </div>
  );
};

export default Header;
