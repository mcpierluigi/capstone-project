import { useEffect } from "react";
import { Container, Button } from "react-bootstrap";
import Title from "./Title";
import { useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { getAllPosts, getUserLoggedAction } from "../redux/actions";

const Home = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getUserLoggedAction());
    dispatch(getAllPosts("http://localhost:3142/users/me/posts"));
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  return (
    <>
      <Container fluid>
        <Title />
        <Container>
          <Link className="nav-link" to={"/registration"}>
            <Button variant="dark" title="signUp">
              Registrati!
            </Button>
          </Link>
          <Link className="nav-link mt-3" to={"/Login"}>
            <Button variant="dark" title="signIn">
              Effettua il Login
            </Button>
          </Link>
        </Container>
      </Container>
    </>
  );
};

export default Home;
