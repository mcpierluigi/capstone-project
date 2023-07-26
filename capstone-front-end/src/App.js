import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Home from "./components/Home";
import Login from "./components/Login";
import LoggedHome from "./components/LoggedHome";
import WritePost from "./components/WritePost";
import Registration from "./components/Registration";
import YourLibrary from "./components/YourLibrary";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import CreateProduct from "./components/CreateProduct";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/registration" element={<Registration />} />
          <Route path="/writePost" element={<WritePost />} />
          <Route path="/loggedHome" element={<LoggedHome />} />
          <Route path="/writePost" element={<WritePost />} />
          <Route path="/yourLibrary" element={<YourLibrary />} />
          <Route path="/createProduct" element={<CreateProduct />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
