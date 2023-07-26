import { useSelector } from "react-redux";
import HomeNavbar from "./HomeNavbar";
import { Link } from "react-router-dom";
import { Container, Button, Row, Col } from "react-bootstrap";
import Footer from "./Footer";
import Card from "react-bootstrap/Card";

const LoggedHome = () => {
  const user = useSelector(state => state.home.user);
  console.log(user);
  return (
    <>
      <HomeNavbar />
      <Container className="text-center pt-4">
        <Link className="nav-link" to={"/writePost"}>
          <Button variant="dark" title="writePost">
            Scrivi un Post
          </Button>
        </Link>
        <Link className="nav-link mt-4" to={"/yourLibrary"}>
          <Button variant="dark" title="yourLibrary">
            Vai alla tua Libreria
          </Button>
        </Link>
        <Container className="d-flex">
          <div>
            <Row sm={2} md={3}>
              {user.posts.map(post => (
                <Col>
                  <Card style={{ width: "15rem", height: "16rem", backgroundColor: "lightgrey" }} className="my-4">
                    <Card.Body>
                      <Card.Title>{post.title}</Card.Title>
                      <Card.Subtitle className="mb-2 text-muted">{post.category}</Card.Subtitle>
                      <Card.Text className="text-card">{post.content}</Card.Text>
                      <Button className="justify-content-center mb-1" variant="primary">
                        Modify
                      </Button>
                    </Card.Body>
                  </Card>
                </Col>
              ))}
            </Row>
          </div>
        </Container>
      </Container>
      <Footer />
    </>
  );
};

export default LoggedHome;
