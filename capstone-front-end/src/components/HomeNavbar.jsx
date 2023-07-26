import Container from "react-bootstrap/Container";
import { useSelector } from "react-redux";
import Navbar from "react-bootstrap/Navbar";

function HomeNavbar() {
  const user = useSelector(state => state.home.user);
  console.log(user);
  return (
    <Navbar className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="#home">Welcome to your Rocket</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end">
          <Navbar.Text>Signed in as: {user.name}</Navbar.Text>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default HomeNavbar;
