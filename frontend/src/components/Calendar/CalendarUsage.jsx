import React from "react";

const CalendarUsage = () => {
  return (
    <div className="calendar__usage">
      <div className="usage__current">
        <span className="current__title">이번달 수입 / 지출 내역</span>
        <span className="current__total profit">+ 2,000,000</span>
      </div>
      <div className="usage__prev">
        <span className="prev__title">지난달 내역 / 비교</span>
        <span className="prev__total profit prev">+ 1,300,000</span>
        <span className="prev__compare loss prev">+ 700,000</span>
      </div>
    </div>
  );
};

export default CalendarUsage;
