import React from "react";
import "./Hero.css";
import Carousel from "react-material-ui-carousel";
import { Paper } from "@mui/material";
import { Port } from "../../types";

// Define the props structure explicitly
interface HeroProps {
  movies: Port[];
}

export const Hero: React.FC<HeroProps> = ({ movies }) => {  // Destructure `movies` from props
  return (
    <div className="movie-carousel-container">
      <Carousel>
        {movies.map((movie) => (
          <Paper key={movie.portId}>  {/* Always add a unique key when mapping */}
            <div className="movie-card-container">
              <div className="movie-card">
                <h4>{movie.portId}</h4>
              </div>
            </div>
          </Paper>
        ))}
      </Carousel>
    </div>
  );
};