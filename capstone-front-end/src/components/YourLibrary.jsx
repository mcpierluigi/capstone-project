import { useEffect } from "react";
import { Button, Container } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { getMeLibrary, getUserLoggedAction } from "../redux/actions";
import HomeNavbar from "./HomeNavbar";

const YourLibrary = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getUserLoggedAction());
    dispatch(getMeLibrary());
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  return (
    <>
      <HomeNavbar />
      <Container className="text-center pt-4">
        <Link className="nav-link" to={"/createProduct"}>
          <Button variant="success" title="createProduct">
            Carica un tuo prodotto 🚀
          </Button>
        </Link>
      </Container>
    </>
  );
};

export default YourLibrary;
