import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useSelector } from "react-redux";

const CalendarHistoryList = () => {
  const historyData = useSelector((reducer) => reducer.calendarReducer);

  return (
    <ul className="history__list">
      {historyData.date.map((item, key) => (
        <li className="list__item" key={key}>
          <span className="item__tag"></span>
          <span className="item__type">{item.kind.name}</span>
          <span className="item__tag-name">{item.category.name}</span>
          <span className="item__title">
          {item.title}
          </span>
          <span className="item__usage">
            {item.kind.id === 1 ? "+" : "-"} {item.price}
          </span>
        </li>
      ))}
    </ul>
  );
};

export default CalendarHistoryList;
