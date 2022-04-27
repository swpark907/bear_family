import React from 'react';

const Terms = () => {
  return (
    <div>
        <h3>약관동의</h3>
        <ul>
          전체약관 동의
          <ul>
            {/* li는 내용 받아와서 컴포넌트로 */}
            <li>약관</li>
            <li>약관</li>
            <li>약관</li>
            <li>약관</li>
            <li>약관</li>
          </ul>
          선택약관 동의
          <ul>
            {/* li는 내용 받아와서 컴포넌트로 */}
            <li>약관</li>
            <li>약관</li>
          </ul>
        </ul>
      </div>
  );
};

export default Terms;