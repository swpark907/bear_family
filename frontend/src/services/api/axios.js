import axios from "axios";

const DOMAIN = "http://146.56.185.52/";

export const request = async ({ method, url, data }) => {
  return axios({
    method,
    url: DOMAIN + url,
    data,
  })
    .then((res) => res.data)
    .catch((err) => console.log(err));
};
