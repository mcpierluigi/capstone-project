import { Container } from "react-bootstrap";

const Title = () => {
  return (
    <Container className="justify-content-center title">
      <div className="pt-4 mx-2">
        <h1 className="display-1 mt-5">Your Rocket</h1>
        <div className="display-5 fs-2 align-bottom mt-5">
          <p>Ti sei mai chiesto cosa porteresti con te nello spazio? </p>
          <p>Unisciti a Your Rocket!</p>
        </div>
      </div>
    </Container>
  );
};

export default Title;
